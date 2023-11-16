package com.example.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author wkk
 * @date 2023-11-16
 */
public class Test {

    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.get(1));
    }
}
