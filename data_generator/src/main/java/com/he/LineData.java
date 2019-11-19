package com.he;

import com.he.invoke.RandomNum;

public class LineData {
    /**
     * 词分隔符
     */
    private static final String wordSeparator = "\t";
    /**
     * 行分隔符
     */
    private static final String lineSeparator = "\n";
    /**
     * 电影名单
     */
    private static final String[] movieArray = {"情圣", "铁道飞虎", "酒国英雄之摆渡人", "血战钢锯岭", "那年夏天你去了哪", "冰雪女皇之冬日魔", "你好，疯子", "罗曼蒂克消亡史"};
    /**
     * 地区名单
     */
    private static final String[] regionArray = {"成都", "北京", "上海", "广州", "重庆", "福建", "深圳", "杭州", "天津"};
    /**
     * 票价范围
     */
    private static final int[] fareArray = {20, 25, 30};

    /**
     * 获取一行数据
     *
     * @return
     */
    public static String getLineData() {

//        循环添加数据
        String movieData = movieArray[RandomNum.getRandomNum(movieArray.length)] + wordSeparator +
                regionArray[RandomNum.getRandomNum(regionArray.length)] + wordSeparator +
                fareArray[RandomNum.getRandomNum(fareArray.length)] + lineSeparator;
        return movieData;
    }
}
