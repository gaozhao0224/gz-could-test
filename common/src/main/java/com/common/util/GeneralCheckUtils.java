package com.common.util;

import com.common.context.StringPool;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralCheckUtils {

    private static final Map<String, String> areaMap = new HashMap<>();

    static {
        areaMap.put("11", "北京");
        areaMap.put("12", "天津");
        areaMap.put("13", "河北");
        areaMap.put("14", "山西");
        areaMap.put("15", "内蒙古");
        areaMap.put("21", "辽宁");
        areaMap.put("22", "吉林");
        areaMap.put("23", "黑龙江");
        areaMap.put("31", "上海");
        areaMap.put("32", "江苏");
        areaMap.put("33", "浙江");
        areaMap.put("34", "安徽");
        areaMap.put("35", "福建");
        areaMap.put("36", "江西");
        areaMap.put("37", "山东");
        areaMap.put("41", "河南");
        areaMap.put("42", "湖北");
        areaMap.put("43", "湖南");
        areaMap.put("44", "广东");
        areaMap.put("45", "广西");
        areaMap.put("46", "海南");
        areaMap.put("50", "重庆");
        areaMap.put("51", "四川");
        areaMap.put("52", "贵州");
        areaMap.put("53", "云南");
        areaMap.put("54", "西藏");
        areaMap.put("61", "陕西");
        areaMap.put("62", "甘肃");
        areaMap.put("63", "青海");
        areaMap.put("64", "宁夏");
        areaMap.put("65", "新疆");
        areaMap.put("71", "台湾");
        areaMap.put("81", "香港");
        areaMap.put("82", "澳门");
        areaMap.put("91", "国外");
    }

    private GeneralCheckUtils() {
        throw new IllegalStateException(StringPool.UTILITY_CLASS);
    }

    /**
     * 检验身份证号码是否符合规范
     * @param idCard 身份证号码
     * @param nullable 是否允许空
     */
    public static void checkIdCard(String idCard, boolean nullable) {
        //如果允许空，空则直接返回，否则校验格式
        if (nullable && StringUtils.isEmpty(idCard)) {
            return;
        }
        //如果不允许空，空则直接抛异常，否则校验格式
        if (!nullable && StringUtils.isEmpty(idCard)) {
            throw new IllegalArgumentException("身份证号码不能为空！");
        }

        // 判断号码的长度 15位或18位
        if (idCard.length() != 15 && idCard.length() != 18) {
            throw new IllegalArgumentException("身份证号码长度应该为15位或18位！");
        }

        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        String Ai;
        if (idCard.length() == 18) {
            Ai = idCard.substring(0, 17);
        } else {
            Ai = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
        }
        if (!StringUtils.isNumeric(Ai)) {
            throw new IllegalArgumentException("身份证15位号码都应为数字；18位号码除最后一位外，都应为数字！");
        }

        // 判断出生年月是否有效
        Integer year = Integer.valueOf(Ai.substring(6, 10));// 年份
        Integer month = Integer.valueOf(Ai.substring(10, 12));// 月份
        Integer day = Integer.valueOf(Ai.substring(12, 14));// 日期
        if (!checkDate(year + StringPool.DASH + month + StringPool.DASH + day)) {
            throw new IllegalArgumentException("身份证出生日期无效！");
        }
        LocalDate localDate = LocalDate.now();
        if ((localDate.getYear() - year) > 150 || localDate.isBefore(LocalDate.of(year, month, day))) {
            throw new IllegalArgumentException("身份证生日不在有效范围！");
        }

        if (month > 12 || month == 0) {
            throw new IllegalArgumentException("身份证月份无效！");
        }
        if (day > 31 || day == 0) {
            throw new IllegalArgumentException("身份证日期无效！");
        }

        // 如果身份证前两位的地区码不在HashMap，则地区码有误
        if (areaMap.get(Ai.substring(0, 2)) == null) {
            throw new IllegalArgumentException("身份证地区编码错误！");
        }

        if (!isVarifyCode(Ai, idCard)) {
            throw new IllegalArgumentException("身份证校验码无效，不是合法的身份证号码！");
        }
    }

    /*
     * 判断第18位校验码是否正确 第18位校验码的计算方式：
     * 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
     * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * 2. 用11对计算结果取模 Y = mod(S, 11)
     * 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     */
    private static boolean isVarifyCode(String Ai, String idCard) {
        String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (idCard.length() == 18 && !Ai.equals(idCard)) {
            return false;
        }
        return true;
    }

    /**
     * 校验日期是否合法：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     * @param strDate 日期
     * @return
     */
    public static boolean checkDate(String strDate) {
        String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$";
        return checkRegex(regex, strDate);
    }

    /**
     * 校验邮箱是否合法
     * @param email 邮箱
     * @param nullable 是否允许空
     */
    public static void checkEmail(String email, boolean nullable) {
        //如果允许空，空则直接返回，否则校验格式
        if (nullable && StringUtils.isEmpty(email)) {
            return;
        }
        //如果不允许空，空则直接抛异常，否则校验格式
        if (!nullable && StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("邮箱不能为空！");
        }

        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (!checkRegex(regex, email)) {
            throw new IllegalArgumentException("邮箱格式不正确！");
        }
    }

    /**
     * 校验手机号码是否合法
     * @param mobilePhone 手机号码
     * @param nullable 是否允许空
     */
    public static void checkMobilePhone(String mobilePhone, boolean nullable) {
        //如果允许空，空则直接返回，否则校验格式
        if (nullable && StringUtils.isEmpty(mobilePhone)) {
            return;
        }
        //如果不允许空，空则直接抛异常，否则校验格式
        if (!nullable && StringUtils.isEmpty(mobilePhone)) {
            throw new IllegalArgumentException("手机号码不能为空！");
        }

        String regex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$";
        if (!checkRegex(regex, mobilePhone)) {
            throw new IllegalArgumentException("手机号码格式不正确！");
        }
    }

    /**
     * 校验座机号码是否合法
     * @param fixedPhone 座机号码
     * @param nullable 是否允许空
     */
    public static void checkFixedphone(String fixedPhone, boolean nullable) {
        //如果允许空，空则直接返回，否则校验格式
        if (nullable && StringUtils.isEmpty(fixedPhone)) {
            return;
        }
        //如果不允许空，空则直接抛异常，否则校验格式
        if (!nullable && StringUtils.isEmpty(fixedPhone)) {
            throw new IllegalArgumentException("座机号码不能为空！");
        }

        String regex = "^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
        if (!checkRegex(regex, fixedPhone)) {
            throw new IllegalArgumentException("座机号码格式不正确！");
        }
    }

    /**
     * 校验用户账号是否合法
     * @param usercode 账号
     */
    public static void checkUsercode(String usercode) {
        if (StringUtils.isEmpty(usercode)) {
            throw new IllegalArgumentException("账号不能为空！");
        }

        //账号格式为5-20位的以字母开头的字母，数字，下划线，减号字符串
        String regex = "^[a-zA-z][a-zA-Z0-9_-]{4,19}$";
        if (!checkRegex(regex, usercode)) {
            throw new IllegalArgumentException("账号格式不正确！");
        }
    }

    /**
     * 校验昵称是否合法
     * @param username 昵称
     */
    public static void checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("昵称不能为空！");
        }

        //昵称格式为4-16位的中文，英文，数字，下划线，减号字符串，中文为2位
        String regex = "^[\\u4E00-\\u9FA5A-Za-z0-9_-]+$";
        if (!checkRegex(regex, username) || (length(username) < 4 || length(username) > 16)) {
            throw new IllegalArgumentException("昵称格式不正确！");
        }
    }

    /**
     * 计算包含中文字符串长度
     * @param str
     * @return
     */
    public static int length(String str) {
        int length = 0;
        String chinese = "[\\u4E00-\\u9FA5]";
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 1);
            if (temp.matches(chinese)) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length;
    }

    /**
     * 校验密码是否合法
     * @param password 密码
     */
    public static void checkPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空！");
        }

        //密码格式为至少8位的字母加数字
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
        if (!checkRegex(regex, password)) {
            throw new IllegalArgumentException("密码格式不正确！");
        }
    }

    /**
     * 校验字符串是否符合正则表达式
     * @param regex 正则表达式
     * @param str 字符串
     * @return
     */
    public static boolean checkRegex(String regex, String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return str.matches(regex);
    }

    /**
     * 校验字符串是否包含正则表达式
     * @param regex 正则表达式
     * @param str 字符串
     * @return
     */
    public static boolean containRegex(String regex, String str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
