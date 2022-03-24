package com.copy;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

/**
 * @ClassName: CopyTest
 * @Description:
 * @Author: GaoZhao
 * @Date 2022/2/11
 */
@Data
class CopyTest1 {
    String name ;

}
@Data
class CopyTest2 {
    String name ;

}
class Test111{
    public static void main(String[] args) {
        CopyTest1 copyTest1 = new CopyTest1();
        copyTest1.setName("gz");
        CopyTest2 copyTest2 = new CopyTest2();
        BeanUtil.copyProperties(copyTest1,copyTest2);
        System.out.println(copyTest2);
    }
}
