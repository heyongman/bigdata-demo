package com.he.invoke;

import java.util.Random;

/**
 * 产生一个随机数，不包含n
 */
public class RandomNum {
    /**
     * 产生一个从0到end的随机数，不包含end
     * @param end
     * @return
     */
    public static int getRandomNum(int end){
            Random random = new Random();
            return random.nextInt(end);
    }

    /**
     * 获取一个从start到end的随机数，不包含end
     * @return
     */
    public static int getRandomNumSE(int start,int end){
        Random random = new Random();
        return random.nextInt(end-start)+start;
    }
}
