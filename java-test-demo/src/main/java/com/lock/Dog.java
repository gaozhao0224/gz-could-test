package com.lock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Dog {
    private String name ;

    public Dog(String name) {
        this.name = name;
    }
    public Dog() {
    }
}