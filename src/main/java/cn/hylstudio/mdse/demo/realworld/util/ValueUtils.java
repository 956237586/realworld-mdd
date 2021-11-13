package cn.hylstudio.mdse.demo.realworld.util;

import cn.hylstudio.mdse.demo.realworld.exception.BizException;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
}
