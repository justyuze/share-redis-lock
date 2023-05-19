package demo.iyushu.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class LockService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript unlockScript;

    public Lock lock(String key, String value, int timeout){
        boolean success = false;
        long start = System.currentTimeMillis();

        while(!success && System.currentTimeMillis() - start <= 20000){
            success =  redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        }

        if(success){
            Lock lock = new Lock(key, value, timeout, new WatchDog());
            lock.getWatchDog().start();
            return lock;
        }

        return null;
    }

    public void unlock(Lock lock){
        redisTemplate.execute(unlockScript, Arrays.asList(lock.getKey()), lock.getValue());
        lock.getWatchDog().stop();
    }

    public class WatchDog implements Runnable {

        private Lock lock;

        public void setLock(Lock lock) {
            this.lock = lock;
        }

        private Thread thread;

        public void start(){
            thread = new Thread(this);
            thread.start();
        }

        public void stop(){
            thread.interrupt();
        }


        @Override
        public void run() {
            long start = System.currentTimeMillis();
            while(!thread.isInterrupted()){
                long current = System.currentTimeMillis();
                if(current - start >= 1000){
                    //System.out.println("续期。。。");
                    redisTemplate.opsForValue().getAndExpire(lock.getKey(), lock.getTimeout(), TimeUnit.SECONDS);
                    start = current;
                }
            }

        }
    }
}
