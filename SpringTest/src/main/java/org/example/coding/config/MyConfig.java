package org.example.coding.config;


import org.example.coding.publishevent.Payload;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.plaf.metal.MetalBorders;


public class MyConfig {

    public Payload getPayload() {
        return new Payload("大哥我出来了喔");
    }
}
