package org.coding.redisson;

//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class MyRedis {
    public static Integer count = 0;

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/lock")
    public String lockTest() {
        RLock lock = redissonClient.getLock("redisson_lock");
//        lock.lock();
        if (!lock.tryLock()) {
            System.out.println("unlock");
            return "unlock";
        }
        try {
            count++;
        } catch (Exception e) {
            System.out.println("exception");
        } finally {
            lock.unlock();
        }
        System.out.println(count);
        return count.toString();
    }


}
