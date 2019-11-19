package com.he.lineData;


import com.he.invoke.RandomName;

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
     * 名字
     */
    private static String[] name = {};
    private static String[] region = {};

    /**
     * 获取一行数据，格式：张三 20
     *
     * @return
     */
    public static String getLineData() {
//        循环添加数据
        StringBuffer sb = new StringBuffer();
        sb.append(RandomName.getChineseName()).append(wordSeparator).append("").append(lineSeparator);
        return sb.toString();
    }
}
