package com.carving.manager;

import org.junit.Test;

import java.util.UUID;

/**
 * @author Li Songlin
 * @date 2019/11/14 10:12
 **/
public class ToolsUtil {

    @Test
    public void generalUUiID() {
        String uuid = UUID.randomUUID().toString();

        System.out.println(uuid.replaceAll("-", ""));
    }
}
