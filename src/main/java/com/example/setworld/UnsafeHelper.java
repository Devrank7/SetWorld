package com.example.setworld;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeHelper {

    private static final Unsafe unsafe;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to access Unsafe", e);
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
