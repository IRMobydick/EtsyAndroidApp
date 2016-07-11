package com.appboy.support;

import java.util.Random;

public final class IntentUtils {
    private static final Random f1040a;

    static {
        f1040a = new Random();
    }

    public static int getRequestCode() {
        return f1040a.nextInt();
    }
}
