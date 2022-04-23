package com.test.enumtest;


public enum StateEnum {


    CG("1","成功"),
    SB("2","失败"),
    DD("3","待定"),

    ;

    private final String key ;
    private final String value ;


    StateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String key() {
        return this.key;
    }

    public String value() {
        return this.value;
    }

    public String getValue(String type) {
        StateEnum[] stateEnums = values();
        for (StateEnum stateEnum : stateEnums) {
            if (stateEnum.key().equals(key)) {
                return stateEnum.value();
            }
        }
        return null;
    }

}
