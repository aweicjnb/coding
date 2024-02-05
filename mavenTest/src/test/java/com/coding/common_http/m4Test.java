package com.coding.common_http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Collections;

@Slf4j
public class m4Test {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.m4(new String("1"), new Integer[]{2, 3}, 4));
    }

    @Test
    public void blankTest() {
        log.info("caskjssads", "哈哈哈");
        System.out.println(StringUtils.isEmpty("   "));
        System.out.println(StringUtils.isBlank("   r   "));
        System.out.println(Collections.EMPTY_LIST);
    }

    @Test
    public void percent() {
        System.out.println(specialChannelPercent(0L));
        System.out.println(specialChannelPercent(1L));
        System.out.println(specialChannelPercent(11L));
        System.out.println(specialChannelPercent(90L));


        int a = 0;
        int b = 0;
        for (long i = 0; i <= 1000; ++i) {
            if (specialChannelPercent(i)) {
                a++;
            } else {
                b++;
            }

        }
        System.out.println(a);
        System.out.println(b);
//        System.out.println(100 % 100);
    }

    public boolean checkNeedInCommon(Long distinct) {
        return distinct > 0;
    }

    public boolean specialChannelPercent(Long distinct){
        if (0L == distinct) {
            return checkNeedInCommon(distinct);
        }
        return distinct % 100 < 10;
    }





    @Test
    public void logTest() {
        try  {
            int a = 1/0;
        } catch (RuntimeException e) {
            /**
             * 因为不能分辨第二个参数是模板的参数 还是普通异常参数
             * log.info(String, Object)
             * log.info(String, throwable)
             */
            log.info("想测试这样写是否有歧义{}",e);
            log.info("这样写就可以{}, {}",e, 2);
            log.info("想测试这样写是否有歧义:{}", "看到没果然有问题");
        }

    }

    @Test
    public void stringUtilsTest() {
        String str = "";
        System.out.println(StringUtils.isBlank(str));
    }

}
