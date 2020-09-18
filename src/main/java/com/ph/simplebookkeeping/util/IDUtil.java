package com.ph.simplebookkeeping.util;

import java.util.UUID;

/**
 * 获取一个新的UUID
 *
 * @author 彭海
 * @date 2019年12月25日
 */
public class IDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
