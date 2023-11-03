package com.example.demo.algorithm;

/**
 * 哈希算法
 */
public class SimpleHash {

    public static void main(String[] args) {

    }

    /* 加法哈希 对输入的每个字符的 ASCII 码进行相加，将得到的总和作为哈希值。 */
    int addHash(String key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        for (char c : key.toCharArray()) {
            hash = (hash + (int) c) % MODULUS;
        }
        return (int) hash;
    }

    /* 乘法哈希 利用了乘法的不相关性，每轮乘以一个常数，将各个字符的 ASCII 码累积到哈希值中。 */
    int mulHash(String key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        for (char c : key.toCharArray()) {
            hash = (31 * hash + (int) c) % MODULUS;
        }
        return (int) hash;
    }

    /* 异或哈希 将输入数据的每个元素通过异或操作累积到一个哈希值中。 */
    int xorHash(String key) {
        int hash = 0;
        final int MODULUS = 1000000007;
        for (char c : key.toCharArray()) {
            hash ^= (int) c;
        }
        return hash & MODULUS;
    }

    /* 旋转哈希 将每个字符的 ASCII 码累积到一个哈希值中，每次累积之前都会对哈希值进行旋转操作。 */
    int rotHash(String key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        for (char c : key.toCharArray()) {
            hash = ((hash << 4) ^ (hash >> 28) ^ (int) c) % MODULUS;
        }
        return (int) hash;
    }
}
