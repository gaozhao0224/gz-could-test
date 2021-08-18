import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.HempApricotApplication;
import com.crc.entity.SysUserTest;
import com.crc.service.ISysUserTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HempApricotApplication.class)
public class TestWeb {
    @Autowired
    private ISysUserTestService sysUserTestService;
    @Test
    public void testList(){
        //QueryWrapper<SysUserTest> queryWrapper = new QueryWrapper<>();
        SysUserTest byId1 = sysUserTestService.getById("1");
        System.out.println(byId1.hashCode()+"++++++++++++++++"+byId1);
        SysUserTest byId2 = sysUserTestService.getById("1");
        System.out.println(byId2.hashCode()+"++++++++++++++++"+byId2);
        SysUserTest byId3 = sysUserTestService.getById("1");
        System.out.println(byId3.hashCode()+"++++++++++++++++"+byId3);

    }


}