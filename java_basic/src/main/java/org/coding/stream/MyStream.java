package org.coding.stream;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyStream {
    public static void main(String[] args) {
        User user1 = new User("大海", 2);
        User user2 = new User("大海", 2);
        User user3 = new User("大海", 3);
        User user4 = new User("大海", 4);
        User user5 = new User("小海", 2);
        List<User> list = Arrays.asList(user1, user2, user3, user4 ,user5);

        distinctTest(list);
    }

    /**
     * 去除重复数据
     * @param list
     */
    public static void distinctTest(List<User> list) {
        //直接更具hashcode+equals比较的 这个方法使用场景比较局限
        list.stream().distinct().forEach(item -> {
            System.out.println(item);
        });

        System.out.println("------");

        //将数据先保存在map 然后打印出来；说白了就是根据hashmap的key是唯一性来弄得
        list.stream().collect(Collectors.toMap(User::getName, user -> user, (a, b) -> a))
                .values()
                .stream()
                .collect(Collectors.toList())
                .forEach(item -> {
                    System.out.println(item);
                });

        //list -> array
        Object[] array = list.stream().toArray();
    }
}

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
class User {
    String name;
    Integer age;
}
