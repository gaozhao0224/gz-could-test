import com.example.WebDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebDemoApplication.class)
public class TestRedis {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void redis(){
        String s = redisTemplate.opsForValue().get("LB_ZUUL_ROUTE_KEY");
        System.out.println(s);
    }


}