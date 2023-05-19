package demo.iyushu.service.a.service;

import demo.iyushu.file.service.FileService;
import demo.iyushu.file.service.Lock;
import demo.iyushu.file.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class CountService {


    private FileService fileService;

    private LockService lockService;

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    public int getCount() throws InterruptedException {

        Lock lock = null;
        try{
            // 获取锁
//            lock = lockService.lock("lock", "服务A" + Thread.currentThread().getName(), 3);
//            if(lock == null){
//                throw new RuntimeException("lock failed");
//            }
            int count = fileService.getCount();
            System.out.println("服务A获取到的计数为" + count);

            // 模拟业务逻辑
            Thread.sleep(2000);

            fileService.setCount(count + 1);
            return count;

        }finally {
            // 释放锁
            if(lock != null){
                lockService.unlock(lock);
            }

        }
    }


}
