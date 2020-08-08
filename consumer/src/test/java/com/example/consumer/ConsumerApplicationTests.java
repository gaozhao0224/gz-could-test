package com.example.consumer;

import com.example.consumer.util.PropertySourceProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

    @Autowired
    private PropertySourceProperties propertySourceProperties;
    @Test
    public void contextLoads() {
        System.out.println(propertySourceProperties.toString());
        System.out.println(propertySourceProperties.getName());
    }
    @Test
    public void test1() {
        HashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i),String.valueOf(i));
            //List<CartItem> cartItemList = new ArrayList<CartItem> (cart.getItems());
        }
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);
        }
    }

}
