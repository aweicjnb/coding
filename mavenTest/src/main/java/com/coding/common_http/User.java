package com.coding.common_http;

import lombok.AllArgsConstructor;
import lombok.Data;

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

