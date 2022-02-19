package com.example.demo.tools;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰名称和下划线名称的相互转换
 */
public class DatabaseTool {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();

    }
}
