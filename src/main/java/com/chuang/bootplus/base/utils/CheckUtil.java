package com.chuang.bootplus.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
    // 是否为手机号
    public static boolean isPhone(String phone){
        String regExp = "^1[345789]\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //是否为邮箱
    public static boolean isEmail(String email){
        String reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //是否符合要求长度
    public static boolean legalString(String value,int maxLen,int minLen){
        if(value.length()<minLen||value.length()>maxLen) {
            return false;
        }
        return true;
    }
    public static boolean leagalNumber(Integer value,int maxLen,int minLen){
        if(value<minLen||value>maxLen) {
            return false;
        }
        return true;
    }

    //是否全部为汉字
    public static boolean allChinese(String str){
        String regex="[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        for(char item:str.toCharArray()){
            Matcher matcher = pattern.matcher(String.valueOf(item));
            if(!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    //是否包含汉字
    public static boolean hasChinese(String value){
        // 汉字的Unicode取值范围
        String regex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(value);
        return match.find();
    }

    // 是否包含合法字符
    public static boolean hasSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


}
