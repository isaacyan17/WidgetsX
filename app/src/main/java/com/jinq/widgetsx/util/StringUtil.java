package com.jinq.widgetsx.util;




import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    public static final int NUMBER_LENGTH = 11;

    /**
     * Determine whether the string is empty
     * @param string Need to determine the string
     * @return true:null ,false:not null
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0||string.equals("null");
    }

    /**
     * Need to determine the string
     * @param string string Need to determine the string
     * @return true:not null ,false:null
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * After the removal of spaces to determine whether the string is empty
     * @param string Need to determine the string
     * @return true:null ,false:not null
     */
    public static boolean isEmptyAfterTrim(String string) {
        return string == null ? true : string.trim().length() == 0;
    }

    /**
     * After the removal of spaces to determine whether the string is empty
     * @param string  string Need to determine the string
     * @return true:not null ,false:null
     */
    public static boolean isNotEmptyAfterTrim(String string) {
        return !isEmptyAfterTrim(string);
    }

    /**
     * 为空设置默认�??
     * @param o 目标字符�??
     * @param s 默认�??
     * @return
     */
    public static String dealNull(Object o, String s)  {
        if (o == null || o.toString().trim().equals("") || o.toString().equals("null")) {
            return s;
        }
        return o.toString().trim();
    }

    /**
     * 是否字母
     * @param str 目标字符�??
     * @return
     */
    public static boolean isLetter(String str){
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    /**
     * 截取字符�??
     * @param s 目标字符�??
     * @param length 截取的长�??
     * @return
     */
    public static String subEndString(String s, int length){
        if (s != null && s.length() > length){
            return s.substring(s.length() - length, s.length());
        }
        return s;
    }

    /**
     * 电话号码比较
     * @param s1
     * @param s2
     * @return
     */
    public static boolean phoneNumberCompare (String s1, String s2){
        return subEndString(s1, NUMBER_LENGTH).equals(subEndString(s2, NUMBER_LENGTH));
    }


    /***
     * 字符转换成LONG�??
     * @param Line
     * @param BString
     * @param EString
     * @return
     */
    public static long StoL(String Line, String BString, String EString) {
        long orgStr = 0;
        String srt = null;
        srt = replace(Line,BString,EString);
        if(null==srt || srt.equals("") || srt.equals("null") || srt.equals(" ")) {
            orgStr = Long.parseLong("0");
        }else{
            orgStr = Long.parseLong(srt);
        }
        return orgStr;
    }

    /***
     * 字符型转成BTYE�??
     * @param Line
     * @param BString
     * @param EString
     * @return
     */
    public static byte StoB(String Line, String BString, String EString) {
        byte orgStr = 0;
        String srt = null;
        srt = replace(Line,BString,EString);
        if(null==srt || srt.equals("") || srt.equals("null") || srt.equals(" ")) {
            orgStr = Byte.parseByte("0");
        } else {
            orgStr = Byte.parseByte(srt);
        }
        return orgStr;
    }

    /**
     * 字符转换成INT�??
     * @param Line
     * @param BString
     * @param EString
     * @return
     */
    public static int StrtoInt(String Line, String BString, String EString) {
        int orgStr = 0;
        String srt = null;
        srt = replace(Line,BString,EString);
        if(null==srt || srt.trim().equals("") || srt.equals("null") || srt.equals(" ")) {
            orgStr = Integer.parseInt("0");
        }else{
            orgStr = Integer.parseInt(srt);
        }
        return orgStr;
    }

    /**
     * 字符的截�??
     * @param line        原始字符�??
     * @param beginString 起始字符�??
     * @param endsString  结束字符�??
     * @return
     */
    public static String replace (String line, String beginString, String endsString) {
        String result = "";
        if (line == null) {
            return result;
        }
        int i = 0;
        int j = 0;
        if((i = line.indexOf(beginString, i)) >= 0 && ((j = line.indexOf(endsString, j)) >= 0))
            result = line.substring(i + beginString.length(), j);
        return result;
    }

    /**
     * 字符型转成BTYE�??
     * @param line
     * @param bStr
     * @param eStr
     * @return
     */
    public static byte string2Byte(String line, String bStr, String eStr){
        byte orgStr = 0;
        String srt = null;
        srt = replace(line,bStr,eStr);

        if(null==srt || srt.trim().equals("") || srt.equals("null")) {
            orgStr = Byte.parseByte("0");
        } else {
            orgStr = Byte.parseByte(srt);
        }

        return orgStr;
    }

    /**
     * 将字符串转化成long
     *
     * @param str
     * @return
     */
    public static long stringToLong(String str) {
        long res = 0;
        try {
            if (isNotEmpty(str))
                res = Long.parseLong(str);
            else
                res = 0;
        } catch (Exception e) {
            res = 0;
        }
        return res;
    }

    /**
     * 将字符串转化成long
     *
     * @param str
     * @return
     */
    public static long stringToLong(String str, long value) {
        long res = value;
        try {
            if (isNotEmpty(str)) {
                res = Long.parseLong(str);
            }
        } catch (Exception e) {
            res = value;
        }
        return res;
    }

    /**
     * 字符转换成DOUBLE
     * @param str
     * @param value
     * @return
     */
    public static double string2Double(String str, double value){


        double res = value;
        try {
            if (isNotEmpty(str)) {
                res = Double.parseDouble(str);
            }
        } catch (Exception e) {
            res = value;
        }
        return res;
    }

    /**
     * 将字符串转化成byte
     *
     * @param str
     * @return
     */
    public static byte stringToByte(String str) {
        byte res = -1;
        try {
            if (isNotEmpty(str)) {
                res = Byte.parseByte(str);
            }
        } catch (Exception e) {
            res = -1;
        }
        return res;
    }

    /**
     *
     */
    public static boolean stringToBoolean(String str){
        boolean result = false;
        try{
            if (isNotEmptyAfterTrim(str))
                result = Boolean.parseBoolean(str.trim());
            else
                result = false;
        }catch(Exception e){
            result = false;
        }
        return result;
    }

    /**
     * 将字符串转化成int
     *
     * @param str
     * @return
     */
    public static int stringToInteger(String str) {
        int res = 0;
        try {
            if (isNotEmpty(str))
                res = Integer.parseInt(str);
            else
                res = 0;
        } catch (Exception e) {
            res = 0;
        }
        return res;
    }

    /**
     * 字符串转化为整型
     * @param str
     * @param defaultValue
     * @return
     */
    public static int stringToInteger(String str, int defaultValue) {
        int res = defaultValue;
        try {
            if (isNotEmpty(str))
                res = Integer.parseInt(str);
            else
                res = defaultValue;
        } catch (Exception e) {
            res = defaultValue;
        }
        return res;
    }





    /**
     * 字符转换成LONG�??
     * @param line
     * @param bStr
     * @param eStr
     * @return
     */
    public static long string2Long(String line, String bStr, String eStr){
        long orgStr = 0;

        String srt = null;
        srt = replace(line,bStr,eStr);
        if(null == srt || srt.trim().equals("") || srt.equals("null")) {
            orgStr = Long.parseLong("0");
        } else {
            orgStr = Long.parseLong(srt);
        }
        return orgStr;
    }

    /**
     * 字符转换成DOUBLE�??
     * @param line
     * @param bStr
     * @param eStr
     * @return
     */
    public static double string2Double(String line, String bStr, String eStr){
        double orgStr = 0;

        String srt = null;
        srt = replace(line,bStr,eStr);
        if(null == srt || srt.trim().equals("") || srt.equals("null")) {
            orgStr = Double.parseDouble("0");
        } else {
            orgStr = Double.parseDouble(srt);
        }
        return orgStr;
    }

    /**
     * 字符转换成布尔型
     * @param line
     * @param bStr
     * @param eStr
     * @return
     */
    public static boolean string2Boolean(String line, String trueLine, String bStr, String eStr){
        String srt = replace(line,bStr,eStr);
        if(srt.equals(trueLine)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2�??
     *
     * @param value
     *            指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度�??，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取�??��字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字�??*/
            if (temp.matches(chinese)) {
                /* 中文字符长度�?? */
                valueLength += 2;
            } else {
                /* 其他字符长度�?? */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2�??
     *
     * @param value  指定的字符串
     * @param subIndex  截取长度，以英文计算
     * @return 截取处理的字符串 返回�??..."
     */
    public static String getSubString(String value, int subIndex) {
        String subString = value;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度�??，否则为1 */
        if(value!=null){
            for (int i = 0; i < value.length(); i++) {
                /* 获取�??��字符 */
                String temp = value.substring(i, i + 1);
                /* 判断是否为中文字�??*/
                if (temp.matches(chinese)) {
                    /* 中文字符长度�?? */
                    valueLength += 2;
                } else {
                    /* 其他字符长度�?? */
                    valueLength += 1;
                }
                //计算到达时�?
                if(valueLength > subIndex) {
                    subString = value.substring(0, i) + "...";
                    break;
                }
            }
        }
        return subString;
    }

    /**
     *
     * @description 获取�??��字符串的字符个数（包含中英文，一个中文算2个字符）
     * @param content
     * @return
     */

    public static int getCharacterLength(final String content) {

        if (null == content || "".equals(content)) {
            return 0;
        } else {
            return (content.length());
        }

    }

    /**
     *
     * @description 获取�??��字符串的字符个数（包含中英文，一个中文算2个字符）
     * @param content
     * @return
     */

    public static int getCharacterNum(final String content) {

        if (null == content || "".equals(content)) {
            return 0;
        } else {
            return (content.length() + getChineseNum(content));
        }

    }

    /**
     *
     * @description 返回字符串里中文字或者全角字符的个数
     * @param s
     * @return
     */

    public static int getChineseNum(String s) {

        int num = 0;
        char[] myChar = s.toCharArray();
        for (int i = 0; i < myChar.length; i++) {

            if ((char) (byte) myChar[i] != myChar[i]) {
                num++;
            }
        }
        return num;
    }

    /**
     * 处理浮点型转换成指定字符型，规定小数位数四舍五入
     * @param value  原�?
     * @param digitCnt 保留小数位数
     * @return
     */
    public static String dealDouble(double value, int digitCnt) {
        String convertStr = null;
        try {
            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(digitCnt);
            df.setMaximumFractionDigits(digitCnt);
            convertStr = df.format(value);
            //到这里为真实的�?
            convertStr = convertStr.replaceAll(",","");
            //下面特殊处理满足特殊要求，原值乘以小数位�??
            int index = convertStr.indexOf(".");
            convertStr = convertStr.substring(0, index)
                    + convertStr.substring(index + 1);
        } catch (Exception ex) {

        }
        return convertStr;
    }

    /**
     * 计算连个纬度之间的距离，�??��单位�??0�??
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
    public static String getDistance(double x, double y, double x2, double y2) {
        //		System.out.println("x="+x+"y="+y+"x2="+x2+"y2="+y2);
        //实际距离，度�??111000米，单位�??
        double distance = Math.sqrt(Math.pow((x - x2),2) + Math.pow((y - y2),2))*111000;

        return getIntDistance(distance);
    }

    /**
     * 保留整型 ，最小单位是50�??
     * @param distance
     * @return
     */
    public static String getIntDistance(double distance) {

        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(0);
        String convertStr = df.format(distance);
        //到这里为真实的�?
        convertStr = convertStr.replaceAll(",", "");

        int strLength = convertStr.length();
        //100米以内的情况
        if(strLength <= 2) {
            if(Integer.parseInt(convertStr) > 50) {
                return "100";
            } else {
                return "50";
            }
        } else {//100米以上的情况
            String big = convertStr.substring(0, strLength - 2);
            String small = convertStr.substring(strLength - 2);
            //50米以上就进一�??
            if(Integer.parseInt(small) > 50) {
                convertStr =  Integer.parseInt(big) + 1 + "00";
            } else {
                convertStr =  big + "50";
            }

        }
        return convertStr;

    }
    /**
     * 处理将百度整型化的经纬度信息变成浮点�??
     * 百度整型化末六位小数
     * @param value
     * @return
     */
    public static double dealIntegerToDouble(Integer value) {
        double newValue = -1;
        //保留小数位，这种算法会抛�??有问题，下面结合�??位以上的情况还是用位截取方式
        String xiaoShu = "0." + value%1000000;//
        String tmpStr = String.valueOf(value);
        if(tmpStr.length() > 6) {
            xiaoShu = "0." + tmpStr.substring(tmpStr.length() - 6);
        }

        newValue = Double.parseDouble(xiaoShu);
        //整数位加小数�??
        newValue = value/1000000 + newValue;
        return newValue;
    }

    /**
     * @param str:字符�??
     * @param length:�??��长度
     * @return 正常true,否则false
     */
    public static int isRightLength(String str, int length){
        try{
            if(null!=str&&!"".equals(str)){
                if(str.length()>length){
                    return length;
                }
            }
            return 0;
        }catch(Exception ex){
            return 0;
        }
    }

    /**
     * check price
     * @param s
     * @return
     */
    public static boolean checkPrice(String s) {

        return s.matches("^\\d{1,7}([.]\\d{1,2})?$|^\\d{1,8}([.]\\d)$|^\\d{1,9}$");
    }

    /**
     * 将String类型设为字符类型
     * @param value
     * @return
     */
    public static String setupString(String value) {
        return "\"" + value + "\"";
    }


    /**
     * 判断是否为手机号�??
     * @param phone
     * @return
     */
    public static boolean checkMobilePhone(String phone) {

        // 长度只能�??1�??
        if(phone.length() != NUMBER_LENGTH) {
            return false;
        }

        //只能包含0-9�??+ - 这样的符�??
        for (int i = 0; i < phone.length(); i++) {
            String number = "0123456789";
            if (number.indexOf(phone.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 匹配手机号 2
     * 中国大陆，港澳台
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {
//        if(phone.length() != NUMBER_LENGTH) {
//            return false;
//        }
        //"^1[3|4|5|7|8][0-9]\\d{8}$"
        String patternString = "^[1][3-8]\\d{9}$|^([6|9])\\d{7}$|^[0][9]\\d{8}$|^[6]([8|6])\\d{5}$";
        Pattern pattern = Pattern
                .compile(patternString);
        Matcher m = pattern.matcher(phone);
        return m.matches();
    }


    /**
     * 判断是否为邮�??
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {

        String check = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int)$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);

        return matcher.matches();
    }


    /**
     * unicode转字符串
     * @param str
     * @return
     */
    public static String unicode2Str(String str) {
        StringBuffer sb = new StringBuffer();
        String[] arr = str.split("\\\\u");
        int len = arr.length;
        sb.append(arr[0]);
        for(int i=1; i<len; i++){
            String tmp = arr[i];
            char c = (char) Integer.parseInt(tmp.substring(0, 4), 16);
            sb.append(c);
            sb.append(tmp.substring(4));
        }
        return sb.toString();
    }

    /**
     * 字符串转unicode
     * @param str
     * @return
     */
    public static String str2Unicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] charArr = str.toCharArray();
        for (char ch : charArr) {
            if (ch > 128) {
                sb.append("\\u" + Integer.toHexString(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    /**
     * 数字后面�?.00   123转成123.00
     * @param dou
     * @return
     */
    public static String toPricenum(String dou){
        String str = null;
        if(null == dou || dou.trim().equals("") || dou.equals("null")) {
            return "0.00";
        }else {
            DecimalFormat df = new DecimalFormat("#,###,###.00");
            str = df.format(Double.parseDouble(dou));
        }

        return str;
    }
    /**
     * 根据相对路径得到绝对路径，如果是绝对路径就直接返�??
     * @param url 相对路径
     * @return
     */
    public static String getFullUrl(String url) {
        if (StringUtil.isEmpty(url)) {
            return url;
        }
        if(url.indexOf("http") != -1 && isEmpty(url)) {
            return url;
        }

        return  url;
    }
    /**
     * urlEncode
     */

//    public static String urlEncode(String url){
//        String encodeurl = null;
//        try {
//            encodeurl = java.net.URLEncoder.encode(Constants.V_DOMAIN+url, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String myurl = Constants.V_DOMAIN+Constants.SKIP_URL+"?member_id="+Constants.userInfo.getUserId()+"&url="
//                +encodeurl+"&from=app"+"&sign="+MD5Util.md5(Constants.userInfo.getUserId()+encodeurl+"app"+Constants.KEY_DOMAIN);
//        return myurl;
//    }

    /**
     * 验证密码
     * @param password 字母和数字组合，6-26位
     * @return
     */
    public static boolean checkPassword(String password){
        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,26}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(password);
        return matcher.matches();
    }

    public static boolean checkName(String name) {
        String check = "^[a-zA-Z\\u4e00-\\u9fa5_][a-zA-Z0-9\\u4e00-\\u9fa5_]{5,26}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }

    /**
     * String 价格转整型(台湾币)
     * @param price
     * @return
     */
    public static int priceToInteger(String price){
        if(isEmpty(price)){
            return -1;
        }
        BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(price));
        return bigDecimal.intValue();
    }

    /**
     * 价格去保持整型
     * @param price
     * @return
     */
    public static String priceFormat(String price) {
        if (isEmpty(price)) {
            return price;
        }
        BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(price));
        return String.valueOf(bigDecimal.intValue());
    }
}
