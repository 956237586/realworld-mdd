package cn.hylstudio.mdse.demo.realworld.util;

import cn.hylstudio.mdse.demo.realworld.exception.BizException;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtils {
    private ValueUtils() {
    }

    private static <T> void abstractCheck(T ref, Predicate<T> predicate, Consumer<T> failedCallback) {
        abstractCheck(ref, predicate, null, failedCallback);
    }

    private static <T> void abstractCheck(T ref, Predicate<T> predicate, Consumer<T> succCallback, Consumer<T> failedCallback) {
        if (predicate.test(ref)) {
            if (succCallback != null) succCallback.accept(ref);
        } else {
            if (failedCallback != null) failedCallback.accept(ref);
        }
    }

    private static <T> Consumer<T> throwBizException(String errMsg) {
        return v -> {
            throw new BizException(errMsg);
        };
    }

    public static <T> void nonNull(T ref, String errMsg) {
        abstractCheck(ref, Objects::nonNull, throwBizException(errMsg));
    }

    public static void notEmpty(String val, String errMsg) {
        abstractCheck(val, v -> v != null && !v.trim().isEmpty(), throwBizException(errMsg));
    }

    public static void checkLength(String val, Long min, Long max, String errMsg) {
        abstractCheck(val, v -> {
            if (v == null) {
                return false;
            }
            v = v.trim();
            boolean result = true;
            if (min != null && min >= 0) {
                result = result && v.length() >= min;
            }
            if (max != null && max >= 0) {
                result = result && v.length() < max;
            }
            return result;
        }, throwBizException(errMsg));
    }

    public static void checkRegex(String val, String regex, String errMsg) {
        abstractCheck(val, v -> {
            if (v == null) {
                return false;
            }
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }, throwBizException(errMsg));
    }

    public static <T> void checkNumberRange(Comparable<T> val, T min, T max, String errMsg) {
        abstractCheck(val, v -> {
            if (v == null) {
                return false;
            }
            boolean result = true;
            if (min != null) {
                result = result && val.compareTo(min) >= 0;
            }
            if (max != null) {
                result = result && val.compareTo(max) < 0;
            }
            return result;
        }, throwBizException(errMsg));
    }

    public static void checkTrue(Boolean val, String errMsg) {
        abstractCheck(val, v -> {
            if (v == null) {
                return false;
            }
            return val.equals(true);
        }, throwBizException(errMsg));
    }

    public static void checkFalse(Boolean val, String errMsg) {
        abstractCheck(val, v -> {
            if (v == null) {
                return false;
            }
            return val.equals(false);
        }, throwBizException(errMsg));
    }
}
