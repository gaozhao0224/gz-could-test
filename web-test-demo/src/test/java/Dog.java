import lombok.Data;

import java.io.Serializable;

@Data
public class Dog implements Serializable {
    private String id;
    private String name;
    private String sex;
    private Integer agr;

    public Dog(String id, String name, String sex, Integer agr) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.agr = agr;
    }
    public Dog() {
    }
}