package com.hrs.parceltracker.util;

import com.hrs.parceltracker.common.exception.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import cn.hutool.core.lang.Validator;
import org.apache.commons.lang3.StringUtils;




import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static final String NULL = "null";

    private static final Pattern[] VALID_EMAIL_ADDRESS_REGEX_LIST =
            {
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE), // common
                    // RFC5322
                    Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.\\-]+@[a-zA-Z0-9.\\-]+$", Pattern.CASE_INSENSITIVE), // NOSONAR
                    // for unicode
                    Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*+@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$",Pattern.CASE_INSENSITIVE) // NOSONAR
            };

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isStringEmpty(String value) {
        return StringUtils.isBlank(value);
    }

    public static boolean isStringNotEmpty(String value) {
        return StringUtils.isNotBlank(value);
    }

    public static boolean isStringNull(String value) {
        return isStringEmpty(value) || NULL.equals(value);
    }

    public static boolean isStringNotNull(String value) {
        return !isStringNull(value);
    }

    public static void checkStringEmpty(String name, String value) {
        if (isStringEmpty(value)) {
            throw new StringEmptyValidationException(name);
        }
    }

    public static boolean isCollectionEmpty(Collection<?> value) {
        return CollectionUtils.isEmpty(value);
    }

    public static boolean isCollectionNotEmpty(Collection<?> value) {
        return CollectionUtils.isNotEmpty(value);
    }

    public static void checkCollectionEmpty(String name, Collection<?> value) {
        if (isCollectionEmpty(value)) {
            throw new NullValidationException(name);
        }
    }

    public static boolean isMapEmpty(Map<?, ?> value) {
        return MapUtils.isEmpty(value);
    }

    public static boolean isMapNotEmpty(Map<?, ?> value) {
        return MapUtils.isNotEmpty(value);
    }

    public static void checkMapNull(String name, Map<?, ?> value) {
        if (isMapEmpty(value)) {
            throw new NullValidationException(name);
        }
    }

    public static boolean isArrayEmpty(Object[] value) {
        return value == null || value.length == 0;
    }

    public static boolean isArrayNotEmpty(Object[] value) {
        return !isArrayEmpty(value);
    }

    public static void checkArrayEmpty(String name, Object[] value) {
        if (isArrayEmpty(value)) {
            throw new NullValidationException(name);
        }
    }

    public static void checkObjectNull(String name, Object value) {
        if (value == null) {
            throw new NullValidationException(name);
        }
    }

    public static void checkStringRegex(String regex, String str) {
        if (!Pattern.matches(regex, str)) {
            throw new ParamValidationException(str + " not match regex: " + regex);
        }
    }

    public static boolean isEqualZero(BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }

        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isAnyEqualZero(BigDecimal... values) {
        for (BigDecimal value : values) {
            if (isEqualZero(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNegativeNumber(BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }

        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean isEqualZero(Number value) {
        if (value == null) {
            value = 0;
        }

        return value.equals(0);
    }

    public static boolean isNotEqualZero(Number value) {
        return !isEqualZero(value);
    }

    public static void checkEqualZero(Number value) {
        if (isEqualZero(value)) {
            throw new ParamValidationException("number can't be zero or null");
        }
    }

    public static void checkEqualZero(BigDecimal value) {
        if (isEqualZero(value)) {
            throw new ParamValidationException("number can't be zero or null");
        }
    }

    public static void checkLimit(String attributeName, Number value, Number limit) {
        if (value.longValue() >= limit.longValue()) {
            throw new OverLimitException(attributeName, limit);
        }
    }

    public static void checkOverLimit(String attributeName, Number value, Number limit) {
        if (value.longValue() > limit.longValue()) {
            throw new BusinessException(attributeName + " can't over " + limit);
        }
    }

    public static void checkEmail(String email) {
        boolean isEmail = Validator.isEmail(email);
        if (isEmail) {
            return;
        }
        throw new ParamValidationException("email is illegal");
    }

    public static boolean isNumeric(String code) {
        return StringUtils.isNumeric(code);
    }

    public static boolean isEmail(String emailStr) {
        if (StringUtils.isBlank(emailStr)) {
            return false;
        }
        emailStr = emailStr.trim();
        for (Pattern p : VALID_EMAIL_ADDRESS_REGEX_LIST) {
            Matcher matcher = p.matcher(emailStr);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
    public static boolean isNotEmail(String emailStr) {
        return !isEmail(emailStr);
    }
}