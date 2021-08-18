import com.example.ContractApplication;
import com.example.contract.mapper.BsContractSignLogMapper;
import com.example.contract.mapper.GzPersonMapper;
import com.example.contract.vo.BsContractSignLog;
import com.example.contract.vo.GzPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractApplication.class)
public class BaseTest {

    @Autowired
    private GzPersonMapper gzPersonMapper;
    @Test
    public void test1(){
        System.out.println("1");
    }
    @Test
    public void test2(){
        GzPerson gzPerson = new GzPerson();
        gzPerson.setAge(20);
        gzPersonMapper.insert(gzPerson);
    }
}