package demo.iyushu.file.service;

import org.springframework.data.redis.core.RedisTemplate;


public class Lock{

    private String key;

    private String value;

    // unit is second
    private int timeout;

    private LockService.WatchDog watchDog;


    public Lock(String key, String value, LockService.WatchDog watchDog) {
        this(key, value, 3, watchDog);

    }

    public Lock(String key, String value, int timeout, LockService.WatchDog watchDog) {
        this.key = key;
        this.value = value;
        this.timeout = timeout;
        this.watchDog = watchDog;
        watchDog.setLock(this);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getTimeout() {
        return timeout;
    }

    public LockService.WatchDog getWatchDog() {
        return watchDog;
    }
}
