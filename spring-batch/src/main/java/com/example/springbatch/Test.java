package com.example.springbatch;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;

/**
 * Test
 *
 * @author wkk
 * @date 2024-04-08
 */
public class Test {
    public static void main(String[] args) {
        /**
         * https://github.com/houbb/sensitive-word
         */
        System.out.println(SensitiveWordHelper.replace("去死啊"));
    }
}
