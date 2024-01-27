package com.hrs.parceltracker.util;

import cn.hutool.core.text.CharSequenceUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jimmy
 * @date 2018-11-08
 */
public class StringUtil {

    public static final String CRLF = "\r\n";


    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String removePrefixZero(String str) {
        if (ValidationUtil.isStringEmpty(str)) {
            return str;
        }
        return str.replaceAll("^(0+)", "");
    }

    public static String removeDoubleQuoteFromPrefixAndSuffix(String value) {
        if (ValidationUtil.isStringEmpty(value)) {
            return value;
        }
        return value.replaceAll("^\"", "").replaceAll("\"$", "");
    }

    public static String removeSuffixCrlf(String str) {
        if (ValidationUtil.isStringEmpty(str)) {
            return str;
        }
        return str.replaceAll(CRLF + "$", "");
    }

    public static String toCamelCase(String str){
        return CharSequenceUtil.toCamelCase(str);
    }

    public static String underLineToCamelCase(String str){
        if (str.contains("_")){
            return CharSequenceUtil.toCamelCase(str);
        }
        return str;
    }

    public static boolean isEmpty(final CharSequence cs){
        return StringUtils.isEmpty(cs);
    }

    public static String getNotNull(String str){
        if (isEmpty(str)){
            return "";
        }
        return str;
    }


    /**
     * isEmpty will return ""
     */
    public static String toUpperCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return str.toUpperCase();
    }

    public static String like(String str) {
        if (StringUtils.isEmpty(str)) {
            return "%";
        }
        return "%" + str + "%";
    }
}

