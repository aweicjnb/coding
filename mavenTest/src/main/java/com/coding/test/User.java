package com.coding.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
/*@NoArgsConstructor*/
@AllArgsConstructor
public class User{
    String name;
    Object obj;

    public String getInstance() {
        return this.name;
    }
}

@Data
@AllArgsConstructor
class User2{
    String name;
    String obj;
}

