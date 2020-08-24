package org.yuekeju.common.util;

import java.util.UUID;

public class GenerateUuid {
    public static String randomUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
