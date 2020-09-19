package com.anonymity;

import lombok.Data;

@Data
public abstract class PersonAbstr {

    private String name = "PersonAbstr 父name" ;
    public abstract void getPerson();

}