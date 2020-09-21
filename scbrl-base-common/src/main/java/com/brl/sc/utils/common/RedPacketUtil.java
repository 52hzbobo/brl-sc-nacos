package com.brl.sc.utils.common;

import com.brl.sc.exception.BusinessException;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RedPacketUtil {

    /**
     * 每个红包最小金额，单位为分
     */
    private static final int MIN_MONEY = 1;

    /**
     * 红包金额的离散程度，值越大红包金额越分散
     * 1~3之间
     */
    public static double getDisperse(){
        Double n = (Math.random()*5);
        //System.err.println("离散值:"+n);
        return n;
    }

    /**
     * 根据剩余的红包金额和红包个数，获取一个红包的金额
     * @param amount 剩余金额，单位为元
     * @param count  剩余红包数
     * @return 红包金额， 单位为元
     */
    public static BigDecimal getOneRedBag(BigDecimal amount, int count, double disperse) {
        //将 元*100 转为分
        int money = amount.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        if (money / count < MIN_MONEY) {
            throw new BusinessException("最小金额0.01,份数超过限制");
        }

        //最大值 = 均值*离散程度
        int max = (int) (money * disperse / count);

        //最大值不能大于总金额
        max = max > money ? money : max;
        return new BigDecimal(randomBetweenMinAndMax(money, count, MIN_MONEY, max)).divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP);

    }

    /**
     * 在最小值和最大值之间随机产生一个红包
     * @param money
     * @param count
     * @param min : 最小金额
     * @param max ： 最大金额
     * @return
     */
    private static int randomBetweenMinAndMax(int money, int count, int min, int max) {
        //最后一个红包直接返回
        if (count == 1) {
            return money;
        }
        //最小和最大金额一样，返最小和最大值都行
        if (min == max) {
            return min;
        }
        //最小值 == 均值， 直接返回最小值
        if (min == money / count) {
            return min;
        }
        //min<=随机数bag<=max
        int bag = ((int) Math.rint(Math.random() * (max - min) + min));

        //剩余的均值
        int avg = (money - bag) / (count - 1);
        //比较验证剩余的红包还够不够分(均值>=最小值 是必须条件),不够分的话就是最大值过大
        if (avg < MIN_MONEY) {
            /*
             * 重新随机一个红包，最大值改成本次生成的红包金额
             * 由于 min<=本次红包金额bag<=max, 所以递归时bag是不断减小的。
             * bag在减小到min之间一定有一个值是合适的，递归结束。
             * bag减小到和min相等时，递归也会结束，所以这里不会死递归。
             */
            return randomBetweenMinAndMax(money, count, min, bag);
        } else {
            return bag;
        }
    }

    public static  List<BigDecimal> generateRedPacket(BigDecimal redPacketSumMoney , Integer num){
        //累计单个红包的金额,最后这个数要和amount一致才对
        BigDecimal total = new BigDecimal(0);
        List<BigDecimal> list = Lists.newArrayList();
        // 随机生成离散值
        //double disperse = getDisperse();
        for (int i = 0; i < num; i++) {
            BigDecimal tem = getOneRedBag(redPacketSumMoney.subtract(total), num - i ,  getDisperse());
            total = total.add(tem);
            list.add(tem);
        }
        return list;
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 10 ; i++){
            List<BigDecimal> ls = generateRedPacket(new BigDecimal("1000.31"),5);
            System.err.println("拼手气红包:" + ls);
//            System.err.println("红包个数:" + ls.size());
//            BigDecimal total = BigDecimal.ZERO;
//            for(BigDecimal b : ls){
//                total = total.add(b);
//            }
//            System.err.println("总金额:"+total);
        }

    }

}
