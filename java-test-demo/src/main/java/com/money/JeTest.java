package com.money;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: JeTest
 * @Description: 测试金额分配
 * @Author: GaoZhao
 * @Date 2022/3/11
 */
@Slf4j
public class JeTest {

    public static void main(String[] args) {
        List<NWxzjWxzqxxFpbfMx> nWxzjWxzqxxFpbfMxes = new ArrayList<NWxzjWxzqxxFpbfMx>();
        List<BigDecimal> bigDecimals = new ArrayList<>();
        //随机给定几户 钱随机
        for (int i = 0; i < 10; i++) {
            int i1 = RandomUtil.randomInt(100000);
            BigDecimal firmStand = new BigDecimal(i1 + "").add(new BigDecimal(new Random().nextDouble()).setScale(Numerical.TWO, BigDecimal.ROUND_DOWN));
            bigDecimals.add(firmStand);
            NWxzjWxzqxxFpbfMx nWxzjWxzqxxFpbfMx = new NWxzjWxzqxxFpbfMx();
            nWxzjWxzqxxFpbfMx.setSjftje(firmStand);
            nWxzjWxzqxxFpbfMxes.add(nWxzjWxzqxxFpbfMx);
        }
        BigDecimal total = new BigDecimal("0");
        for (BigDecimal bigDecimal : bigDecimals) {
            total = total.add(bigDecimal);
        }
        System.out.println(bigDecimals);
        System.out.println("支取总额为："+total);
        BigDecimal one = new BigDecimal(RandomUtil.randomInt(total.intValue()) + "");
        BigDecimal two = new BigDecimal("" + RandomUtil.randomInt(total.intValue() - one.intValue()));
        BigDecimal three = total.subtract(one).subtract(two);


//        List<NWxzjWxzqxxFpbfMx> nWxzjWxzqxxFpbfMxes = Arrays.asList(
//                NWxzjWxzqxxFpbfMx.builder().sjftje(new BigDecimal("8000")).build(),
//                NWxzjWxzqxxFpbfMx.builder().sjftje(new BigDecimal("6000")).build());


        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal("0");
        BigDecimal c = new BigDecimal("0");
        List<NWxzjWxzqxxFpbfMx> capitalApportion1 = getCapitalApportion(total, one, nWxzjWxzqxxFpbfMxes);
        for (NWxzjWxzqxxFpbfMx aa : capitalApportion1) {
            a = a.add(aa.getBcbfje());
        }
        System.out.println("----------" + a + "\n");
        List<NWxzjWxzqxxFpbfMx> capitalApportion2 = getCapitalApportion(total, two, nWxzjWxzqxxFpbfMxes);
        for (NWxzjWxzqxxFpbfMx aa : capitalApportion2) {
            b = b.add(aa.getBcbfje());
        }
        System.out.println("----------" + b + "\n");
        List<NWxzjWxzqxxFpbfMx> capitalApportion3 = getCapitalApportion(total, three, nWxzjWxzqxxFpbfMxes);
        for (NWxzjWxzqxxFpbfMx aa : capitalApportion3) {
            c = c.add(aa.getBcbfje());
        }
        System.out.println("----------" + c + "\n");

        System.out.println("总拨付钱为："+a.add(b).add(c));

    }

    /**
     * 资金分摊 处理方法
     * amountWithdrawal 实际支取金额        amountAppropriate 本次拨付金额
     */
    public static List<NWxzjWxzqxxFpbfMx> getCapitalApportion(BigDecimal amountWithdrawal, BigDecimal amountAppropriate, List<NWxzjWxzqxxFpbfMx> nWxzjWxzqxxFpbfMxs) {
        //验证上游数据
        if (null == amountWithdrawal || null == amountAppropriate || amountWithdrawal.compareTo(amountAppropriate) == -1) {
            throw new BizException(CapitalEmployErrorCodeEnum.WXZQXX_ZQBH_CYYC.getFailCode(), CapitalEmployErrorCodeEnum.WXZQXX_ZQBH_CYYC.getFailMsg());
        }
        //按比例计算后得到的拨付金额总和
        BigDecimal appropriateRental = new BigDecimal(Numerical.ZERO);
        //拨付明细应分摊金额总和
        BigDecimal amountAppropriateMx = new BigDecimal(Numerical.ZERO);
        for (int i = 0; i <nWxzjWxzqxxFpbfMxs.size(); i++) {
            //计算本次拨付金额占支取金额的比例  保留十位小数 减小除不尽余额 减少递归次数
            BigDecimal divide = nWxzjWxzqxxFpbfMxs.get(i).getSjftje().divide(amountWithdrawal, 10, BigDecimal.ROUND_DOWN);//舍弃最后一位
            log.info("第"+(i+1)+"户支取实际分摊金额占据总金额的比例： {}; 实际应分摊金额乘以比例的值为：  {}; 保留两位舍弃其他的结果为：  {}", divide, amountAppropriate.multiply(divide), amountAppropriate.multiply(divide).setScale(Numerical.TWO, BigDecimal.ROUND_DOWN));
            nWxzjWxzqxxFpbfMxs.get(i).setBcbfje(amountAppropriate.multiply(divide).setScale(Numerical.TWO, BigDecimal.ROUND_DOWN));
            appropriateRental = appropriateRental.add(nWxzjWxzqxxFpbfMxs.get(i).getBcbfje());
            amountAppropriateMx = amountAppropriateMx.add(nWxzjWxzqxxFpbfMxs.get(i).getSjftje());
        }
        if (amountWithdrawal.compareTo(amountAppropriateMx) != 0) {
            throw new RuntimeException("支取金额有误");
        }
        log.info("本次拨付按比例分摊后的总和： {}", appropriateRental);
        log.info("应支取金额总和： {}", amountAppropriateMx);
        log.info("本次应拨付钱和按比例计算后的差值： {}", amountAppropriate.subtract(appropriateRental));
        if (amountAppropriate.compareTo(appropriateRental) == 0) {
            log.info("按比例分配后可以没有余额");
            return nWxzjWxzqxxFpbfMxs;
        }
        //本次拨付金额必须大于 计算比例后的拨付金额之和
        if (null == amountAppropriate && null == appropriateRental && amountAppropriate.compareTo(appropriateRental) == 1) {
            throw new BizException(CapitalEmployErrorCodeEnum.WXZQXX_ZQBH_MX_FTYC.getFailCode(), CapitalEmployErrorCodeEnum.WXZQXX_ZQBH_MX_FTYC.getFailMsg());
        }
        List<NWxzjWxzqxxFpbfMx> nWxzjWxzqxxFpbfMxes = allocationMoney(nWxzjWxzqxxFpbfMxs, amountAppropriate.subtract(appropriateRental));
        //验证本次拨付的金额分摊之后是否相等
        BigDecimal ultimatelyAppropriate = new BigDecimal("0");
        for (NWxzjWxzqxxFpbfMx nWxzjWxzqxxFpbfMx : nWxzjWxzqxxFpbfMxes) {
            ultimatelyAppropriate = ultimatelyAppropriate.add(nWxzjWxzqxxFpbfMx.getBcbfje());
        }
        if (amountAppropriate.compareTo(ultimatelyAppropriate) != 0) {
            throw new RuntimeException("拨付分摊后金额有误");
        }
        log.info("分摊计算后所有户/幢的拨付总金额:{}", ultimatelyAppropriate);
        return nWxzjWxzqxxFpbfMxes;
    }

    /**
     * @Param: List<NWxzjWxzqxxFpbfMx> 分摊幢/户    balance  未分配余额
     * @Return: List<NWxzjWxzqxxFpbfMx>
     * @Author: GaoZhao
     * @Date: 2022/2/24
     **/
    public static List<NWxzjWxzqxxFpbfMx> allocationMoney(List<NWxzjWxzqxxFpbfMx> objs, BigDecimal balance) {

        for (NWxzjWxzqxxFpbfMx NWxzjWxzqxxFpbfMx : objs) {
            NWxzjWxzqxxFpbfMx.setBcbfje(NWxzjWxzqxxFpbfMx.getBcbfje().add(new BigDecimal(Numerical.PENNY)));
            balance = balance.subtract(new BigDecimal(Numerical.PENNY));
            if (balance.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
        if (balance.compareTo(BigDecimal.ZERO) == 0) {
            return objs;
        } else {
            return allocationMoney(objs, balance);
        }
    }
}
