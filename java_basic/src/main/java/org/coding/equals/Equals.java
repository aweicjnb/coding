package org.coding.equals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Equals {
    public static void main(String[] args) {
        MyEquals1 ss = new MyEquals1("ss", 12);
        MyEquals1 tt = new MyEquals1("ss", 15);
        MyEquals1 yy = new MyEquals1("ss", 62);

//        System.out.println(ss.equals(tt));
        System.out.println(Objects.equals(ss, tt));


        List<MyEquals1> list = Arrays.asList(ss, tt, yy);
        boolean b = list.stream().anyMatch(item -> item.getAge().equals(15));
        System.out.println(b);
    }
}



//@ToString
@Data
//@EqualsAndHashCode
@AllArgsConstructor
class MyEquals1 {
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MyEquals1 myEquals1 = (MyEquals1) object;
        return Objects.equals(name, myEquals1.name) && Objects.equals(age, myEquals1.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
