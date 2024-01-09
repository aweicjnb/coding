import com.alibaba.fastjson.JSON;

import lombok.Data;
import org.junit.Test;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


public class TestTest {
    @Test
    public void test1() {
        int a = 0;
        /**
         * 0 --> 3
         * 1 --> 1,2,3
         * 2 --> 2,3
         * 3 --> 3
         * 4 --> 3
         */
        switch (a) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            default:
                System.out.println(3);
        }


        /**
         * 0 --> 3
         * 1 --> 1,2
         * 2 --> 2
         * 3 --> 3
         * 4 --> 3
         */
        switch (2) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println(3);
        }
    }
}


/*class A {

}*/


/*public class DailyEnergyConsumption {

    public static void main(String[] args) {
        String jsonData = "your_json_data_here";  // 替换为实际的 JSON 数据
        Map<String, Map<String, Double>> dailyEnergyConsumption = calculateDailyConsumption(jsonData);

        if (dailyEnergyConsumption != null) {
            // 输出每个电表每天的用电量差值
            for (Map.Entry<String, Map<String, Double>> entry : dailyEnergyConsumption.entrySet()) {
                System.out.println("MeterId: " + entry.getKey());
                for (Map.Entry<String, Double> dailyEntry : entry.getValue().entrySet()) {
                    System.out.println("   Date: " + dailyEntry.getKey() + ", Daily Energy Consumption: " + dailyEntry.getValue());
                }
            }
        } else {
            System.out.println("Error processing data.");
        }
    }

    private static Map<String, Map<String, Double>> calculateDailyConsumption(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, Double>> dailyEnergyConsumption = new HashMap<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);

            for (JsonNode record : rootNode) {
                String timestampStr = record.get("timestamp").asText();
                String meterId = record.get("meterId").asText();
                double value = record.get("value").asDouble();

                Date date = new Date(Long.parseLong(timestampStr));
                String dayKey = new SimpleDateFormat("yyyy-MM-dd").format(date);

                // 初始化电表每天用电量记录
                dailyEnergyConsumption.putIfAbsent(meterId, new HashMap<>());

                // 处理可能存在的特殊情况
                if (value >= 0) {
                    // 获取前一天的用电量
                    Double prevDayConsumption = dailyEnergyConsumption.get(meterId).getOrDefault(dayKey, 0.0);

                    // 计算当天的用电量差值
                    double dailyConsumption = value - prevDayConsumption;

                    // 存储当天的用电量
                    dailyEnergyConsumption.get(meterId).put(dayKey, dailyConsumption);
                } else {
                    // 处理采集失败或归零情况
                    System.out.println("Warning: Data collection failed or value is negative for meterId: " + meterId);
                }
            }

            return dailyEnergyConsumption;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}*/




class Demo {
    private Map<Long, TreeMap<String, Double>> dailyConsumptionMap = new ConcurrentHashMap<>();

    public void handle(String jsonString) throws Exception {
        new RedissonClient().getLock().lock();
        User user = JSON.parseObject(jsonString, User.class);
        Long timestamp = user.timestamp;
        Long meterId = user.meterId;
        Double value = user.value;

        String today = today(timestamp);
        TreeMap<String, Double> map = dailyConsumptionMap.get(meterId);
        if (map != null && value < map.lastEntry().getValue()) {
            throw new Exception("数据异常");
        }

        if (!dailyConsumptionMap.containsKey(meterId)) {
            map = new TreeMap<>();
            dailyConsumptionMap.put(meterId, map);
            map.put("0", 0d);
        }
        map.put(today, value);
    }


    private void print(Long meterId) {
        Map<String, Double> stringDoubleMap = dailyConsumptionMap.get(meterId);
        Double pre = -1d;
        for (Map.Entry<String, Double> entry: stringDoubleMap.entrySet()) {
            if (pre >= 0) {
                System.out.println(entry.getKey() + "日，居民："+ meterId +" 用电:"+ (entry.getValue() - pre));
            }
            pre = entry.getValue();
        }
    }


    public String today(Long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        LocalDate today = dateTime.toLocalDate();
        String format = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return format;
    }

    public static void main(String[] args) throws Exception {
        Demo tracker = new Demo();

        // 模拟采集到的电表数据
        String jsonString1 = "{\"timestamp\": 1962921600000, \"meterId\": \"1\", \"value\": 100}";
        String jsonString2 = "{\"timestamp\": 1963008000000, \"meterId\": \"1\", \"value\": 150}";
        String jsonString3 = "{\"timestamp\": 1963094400000, \"meterId\": \"1\", \"value\": 210}";

        // 处理电表数据
        tracker.handle(jsonString1);
        tracker.handle(jsonString2);
        tracker.handle(jsonString3);

        // 获取按天统计的电量
        tracker.print(1L);
    }
}

@Data
class User {
    Long timestamp;
    Long meterId;
    Double value;
}





class Node {
    public int x;
    public int y;
    public Node node;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}

 class PathDemo {
    public static int shortPath(int[][] grid, Node start, Node end) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (!check(grid, start.x, start.y, rows, cols) || !check(grid, end.x, end.y, rows, cols)) {
            System.out.println("起点或终点不能到达");
            return -1;
        }

        if (start.x == end.x && start.y == end.y) {
            System.out.println("起点和终点相同");
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);

        int[][] distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], -1);
        }
        distance[start.x][start.y] = 0;

        int[][] dict = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int[] dir : dict) {
                int x = current.x + dir[0];
                int y = current.y + dir[1];

                if (check(grid, x, y, rows, cols) && distance[x][y] == -1) {
                    distance[x][y] = distance[current.x][current.y] + 1;
                    Node nextNode = new Node(x, y);
                    nextNode.setNode(current);
                    queue.offer(nextNode);

                    //判断是否到达目标地点
                    if (x == end.x && y == end.y) {
                        System.out.print("路径：");
                        nextNode = reverse(nextNode);
                        while (nextNode != null) {
                            System.out.print("[" + nextNode.x + ","+ nextNode.y + "],");
                            nextNode = nextNode.node;
                        }
                        System.out.println();
                        return distance[x][y];
                    }
                }
            }
        }

        System.out.println("没有哪条路径可以到达");
        return -1;
    }

    private static boolean check(int[][] grid, int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1;
    }

    private static Node reverse(Node point) {
        Node pre = null;
        Node cur = point;
        while (cur != null) {
            Node next = cur.node;
            cur.node = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 0, 0},
            {1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1}
        };

        //起点
        Node start = new Node(4, 0);
        //终点
        Node end = new Node(6, 3);

        int distance = shortPath(grid, start, end);
        System.out.println("距离: " + ++distance);
    }
}


/**
 * 基于 spring data redis 中提供的redisTempalte，实现分布式锁的功能。
 * 可以参考reddison，要求满足：
 *
 * 1. 能在持有锁的线程意外终止后，自动释放锁。
 * 2. 支持锁的排队重入与超时退出。
 */

/*
@Component
class Redis {
    RedisTemplate redisTemplate;
    MyMessageListener myMessageListener;

    private static final String LOCK = "redis_lock";
    private static final String QUEUE_KEY = "redis_queue";

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    public static ConcurrentHashMap<String, Condition> map = new ConcurrentHashMap<>();

    Redis(RedisTemplate redisTemplate, MyMessageListener myMessageListener) {
        this.redisTemplate = redisTemplate;
        this.myMessageListener = myMessageListener;
        map.put(uid, condition);
    }

    public String uid = getUUID();


    private static String lockScript =
            "if (redis.call('exists', KEYS[1]) == 0) then " +
            "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
            "redis.call('pexpire', KEYS[1], ARGV[1]);" +
            " return nil;" +
            " end;" +
            " if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
            "redis.call('hincrby', KEYS[1], ARGV[2], 1);" +
            " redis.call('pexpire', KEYS[1], ARGV[1]); " +
            "return nil;" +
            " end; " +
            "return redis.call('pttl', KEYS[1]);";
    private static String unLockScript =
            "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then" +
            "return nil;" +
            "end;" +
            "local counter = redis.call('hincrby', KEYS[1], ARGV[1], -1);" +
            "if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]);" +
            "return 0;" +
            "else redis.call('del', KEYS[1]);" +
            "redis.call('publish', KEYS[2], ARGV[1]);" +
            "return 1;" +
            "end;";

    private static Long keepAlive = 1000 * 60 * 5L;

    public String lock() {
        DefaultRedisScript script = new DefaultRedisScript(lockScript, String.class);
        String id = uid + Thread.currentThread().getName();
        return (String) redisTemplate.execute(script, Collections.singletonList(LOCK), keepAlive, id);
    }

    public String unLock() {
        DefaultRedisScript script = new DefaultRedisScript(unLockScript, String.class);
        String id = uid + Thread.currentThread().getName();
        return (String) redisTemplate.execute(script, Arrays.asList(LOCK, QUEUE_KEY), new Object[]{id, keepAlive});
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public void process(Runnable runnable) {
        String res = lock();
        try {
            while (true) {
                if (!res.equals("nil")) {
                    //加入等待队列
                    String id = uid + Thread.currentThread();
                    redisTemplate.opsForList().leftPush(QUEUE_KEY, id);
                    condition.await();
                    res = lock();
                } else {
                    break;
                }
            }

            //task
            runnable.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unLock();
        }

    }
}



*/
/*
//分布式锁
                        KEYS[1] Collections.singletonList(this.getRawName()),
                        //超时时间
                        ARGV[1] new Object[]{unit.toMillis(leaseTime),
                        //服务器唯一节点
                        ARGV[2] this.getLockName(threadId)});

        return this.evalWriteAsync(
                this.getRawName(),
                LongCodec.INSTANCE,
                command,
                "if (redis.call('exists', KEYS[1]) == 0) then " +
                "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                "redis.call('pexpire', KEYS[1], ARGV[1]);" +
                " return nil;" +
                " end;" +
                " if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
                "redis.call('hincrby', KEYS[1], ARGV[2], 1);" +
                " redis.call('pexpire', KEYS[1], ARGV[1]); " +
                "return nil;" +
                " end; " +
                "return redis.call('pttl', KEYS[1]);",





unlock


                KEYS[1] 分布式锁
               *//*
*/
/* KEYS[2] channleName*//*
*/
/*

               *//*
*/
/* ARGV[1] channel发送消息类型*//*
*/
/*
                ARGV[2] 过期时间
                ARGV[3] 服务器唯一节点

    -- 不存在key
    if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then
    return nil;end;local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1);if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]);return 0;else redis.call('del', KEYS[1]);redis.call('publish', KEYS[2], ARGV[1]);return 1;end;

    return nil;*//*





*/
/**@Service
public class DistributedQueueService {

    private static final String LOCK_KEY = "myLock";
    private static final String QUEUE_KEY = "taskQueue";



    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void processWithLockAndQueue(String taskId) {
        // 尝试获取锁
        if (tryAcquireLock()) {
            try {
                // 在获取锁之后，执行业务逻辑

                // 将任务放入队列
                redisTemplate.opsForList().leftPush(QUEUE_KEY, taskId);

            } finally {
                releaseLock(); // 释放锁
            }
        } else {
            // 获取锁失败，将任务放入队列
            redisTemplate.opsForList().leftPush(QUEUE_KEY, taskId);
        }
    }

    public String getNextTaskFromQueue() {
        // 从队列中取出任务，使用 BLPOP 命令阻塞等待
        return redisTemplate.opsForList().rightPop(QUEUE_KEY, 10, TimeUnit.SECONDS);
    }

    private boolean tryAcquireLock() {
        // 使用 SETNX 操作尝试获取锁
        Boolean isLocked = redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "locked", 10, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(isLocked);
    }

    private void releaseLock() {
        // 释放锁，并发布通知
        redisTemplate.delete(LOCK_KEY);
        redisTemplate.convertAndSend("lockReleasedChannel", "Lock Released");
    }
}*//*




@Configuration
class RedisConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic("queue"));

        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new MyMessageListener());
    }
}

@Component
class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String id = new String(message.getBody());
        Redis.map.get(id).signal();
    }
}


*/
