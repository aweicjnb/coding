package org.coding.time;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MyDateTime {
    public static void main(String[] args) {
        /*--------------时间戳----------------*/
        System.out.println(new Date().getTime());
        System.out.println(new Date());
        System.out.println(LocalTime.now());

        /*--------------日期---------------*/
        LocalDate today = LocalDate.now();
        System.out.println(today);
        int year = today.getYear();
        int monthValue = today.getMonthValue();
        Month month = today.getMonth();
        int day = today.getDayOfMonth();
        System.out.println(year + " " + monthValue + " " + day);
        System.out.println(month);

        //特定某个日期
        LocalDate localDate2024_3_12 = LocalDate.of(2024, 3, 12);
        System.out.println(localDate2024_3_12);

        //检查今天是否yours生日
        MonthDay monthDay = MonthDay.of(localDate2024_3_12.getMonthValue(), localDate2024_3_12.getDayOfMonth());
        MonthDay from = MonthDay.from(today);
        System.out.println(from.equals(monthDay));

        //明年的上个月的后天就是你的😧忌日🪦
        int plusYears1 = today.plusYears(1).getYear();
        int minusMonths1 = today.minusMonths(1).getMonthValue();
        int plusDays3 = today.minusDays(-2).getDayOfMonth();
        System.out.println(plusYears1 + " " + minusMonths1 + " " + plusDays3);

        // 你的头七是多久
        LocalDate nextWeeks = today.plusWeeks(1);
        System.out.println(nextWeeks);

        // 日期比较
        LocalDate localDate2024_4_21 = LocalDate.of(2025, 4, 21);
        System.out.println(localDate2024_4_21.isBefore(today));
        System.out.println(localDate2024_4_21);

        //检查闰年
        System.out.println(today.isLeapYear());


        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(zonedDateTime);
    }
}
