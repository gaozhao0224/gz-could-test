package com.copy;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true) //默认 false  如果是true 则get set方法没有前缀get和set
public class Cat {

    private String DogName ;
    private String DogSex ;
    private String DogAge ;

}