package com.brl.sc.utils.string;

import com.brl.sc.utils.date.DateUtil;
import com.brl.sc.utils.pay.IdWorkerUtil;

import java.util.Random;

public class SeqUtil {

    public static int DEFAULT_NUM = 8 ;
    private static final String NUMBER_CHARS = "0123456789";
    public static String generate(){
        return generate(DEFAULT_NUM);
    }

    /**
     * 随机生成 num位数字字符数组
     *
     * @param num
     * @return
     */
    public static String generate(Integer num) {
        char[] rands = new char[num];
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = NUMBER_CHARS.charAt(rand);
        }
        return new String(rands);
    }

    //生成6位随机数字和字母,
    public static String getStringRandom() {
        return getStringRandom(6);
    }

    //生成随机数字和字母,
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 生成范围的随机整数
     * @param min
     * @param max
     * @return
     */
    public static Integer randomNum(int min , int max){
        //创建Random类对象
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 生成订单号（毫秒+随机5位数）
     * @return
     */
    public static String seqOrderNo(){
        return DateUtil.getDateTime4yyyyMMddHH() + IdWorkerUtil.getId();
//        return DateUtil.getMillisecondNo()+generate(5);
    }

    public static void main(String[] args) {
        for(;;){
            System.err.println(randomNum(200,1000));
        }
    }
}
