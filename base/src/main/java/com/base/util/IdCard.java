package com.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by 俞智威
 * on 2016-02-03.
 * 09:26
 * Procedure Explain:身份证号码
 */
public class IdCard {

    public static final String TAG = "IdCard";


    // 每位加权因子
    private int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};


    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     */
    public boolean isValidate18Idcard(String idCard) {
        // 非18位为假
        if (idCard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idcard17 = idCard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idCard.substring(17, 18);
        char[] c;
        String checkCode;
        // 是否都为数字
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }


        int[] bit = converCharToInt(c);
        int sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        } else if (!idcard18Code.equalsIgnoreCase(checkCode)) {    // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
            return false;
        }

        return true;
    }


    /**
     * 数字验证
     */
    public boolean isDigital(String str) {
        return !(str == null || "".equals(str)) && str.matches("^[0-9]*$");
    }


    /**
     * 将15位的身份证转成18位身份证
     */
    public String convertIdcarBy15bit(String idcard) {
        String idcard17 = null;
        // 非15位身份证
        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode = "";

            // 将字符数组转为整型数组
            int[] bit = converCharToInt(c);
            int sum17 = getPowerSum(bit);

            // 获取和值与11取模得到余数进行校验码
            checkCode = getCheckCodeBySum(sum17);
            // 获取不到校验位
            if (null == checkCode) {
                return null;
            }

            // 将前17位与第18位校验码拼接
            idcard17 += checkCode;
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }


    /**
     * 将字符数组转为整型数组
     *
     * @throws NumberFormatException
     */
    public int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }


    /**
     * 15位和18位身份证号码的基本数字和位数验校
     */
    public boolean isIdcard(String idcard) {
        return !(idcard == null || "".equals(idcard)) && Pattern.matches(
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
    }


    /**
     * 15位身份证号码的基本数字和位数验校
     */
    public boolean is15Idcard(String idcard) {
        return !(idcard == null || "".equals(idcard)) && Pattern.matches(
                "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
                idcard);
    }


    /**
     * 验证所有的身份证的合法性
     */
    public boolean isValidatedAllIdcard(String idcard) {
        if (idcard.length() == 15) {
            idcard = this.convertIdcarBy15bit(idcard);
        }
        return this.isValidate18Idcard(idcard);
    }


    /**
     * 18位身份证号码的基本数字和位数验校
     */
    public boolean is18Idcard(String idcard) {
        return Pattern
                .matches(
                        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                        idcard);
    }


    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     */
    public int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     */
    public String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }


}