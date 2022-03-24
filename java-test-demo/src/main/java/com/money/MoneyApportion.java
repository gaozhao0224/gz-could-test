package com.money;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: MoneyApportion
 * @Description: 测试支取和拨付分摊金额
 * @Author: GaoZhao
 * @Date 2022/2/24
 */
public class MoneyApportion {


    public static void main(String[] args) {

        BigDecimal actualWithDrawalAmount = new BigDecimal("10001");//实际支取金额
        BigDecimal rental = new BigDecimal("10001");//实际支取总额

        BigDecimal bcbfje = new BigDecimal("111");//本次拨付金额


        List<Obj> objs = getList();
        //
        BigDecimal divide = actualWithDrawalAmount.divide(rental,10,BigDecimal.ROUND_DOWN);//舍弃最后一位
        System.out.println(divide);

        //按比例计算得到的拨付金额
        BigDecimal appropriateRental = new BigDecimal("0");
        BigDecimal zqje = new BigDecimal("0"); ;
        for (Obj obj : objs) {
            obj.setBfje(obj.getZqje().multiply(divide).setScale(2,BigDecimal.ROUND_DOWN));
            appropriateRental = appropriateRental.add(obj.getBfje());
            zqje = zqje.add(obj.getZqje());
        }
        if(null==bcbfje || null==appropriateRental ||  bcbfje.compareTo(appropriateRental) == -1){
            throw new RuntimeException("金额异常");
        }
        System.out.println("分配金额："+objs.toString());
        System.out.println("拨付按比例分配后金额"+appropriateRental);
        System.out.println("支取金额： "+zqje);
        System.out.println("本次拨付金额： "+bcbfje);
        System.out.println("支取金额-拨付按比例分配后金额： "+bcbfje.subtract(appropriateRental));
        List<Obj> objs1 = allocationMoney(objs, bcbfje.subtract(appropriateRental));
//        List<Obj> objs1 = allocationMoney(objs, new BigDecimal("0.11"));
        appropriateRental = new BigDecimal("0");
        System.out.println("你特么数据呢："+objs1.toString());
        for (Obj obj : objs1) {
            appropriateRental = appropriateRental.add(obj.getBfje());
        }
        System.out.println("拨付按比例分配后最终金额"+appropriateRental);

    }




    //按分递归调用分配金额  增加比例保留位数 减小递归金额 按分递归 不做分前面单位递归
    /**
    * @description:
    * @Param: objs 分摊幢/户    balance  未分配余额
    * @Return: List<T>
    * @Author: GaoZhao
    * @Date: 2022/2/24
    **/
    public static List<Obj> allocationMoney(List<Obj> objs,BigDecimal balance){

        for (Obj obj : objs) {
            obj.setBfje(obj.getBfje().add(new BigDecimal("0.01")));
            balance = balance.subtract(new BigDecimal("0.01"));
            if(balance.compareTo(BigDecimal.ZERO)==0){
                break;
            }
        }
        if (balance.compareTo(BigDecimal.ZERO)==0){
            return objs;
        }else{
            return allocationMoney(objs, balance);
        }
    }


    public static List<Obj> getList(){
        return Arrays.asList(new Obj("张三", new BigDecimal("1221.11"),null), new Obj("李四",
                        new BigDecimal("2522.23"),null), new Obj("王五", new BigDecimal("3844.31"),null),
                new Obj("赵六", new BigDecimal("1960.15"),null), new Obj("孙七", new BigDecimal("452.2"),null));
    }
}
@Data
@AllArgsConstructor
class Obj{
    private String name;
    private BigDecimal zqje ;
    private BigDecimal bfje ;
}
