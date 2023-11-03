package com.example.demo.algorithm;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 二叉树
 */
@Data
public class BinaryTree {
    private Integer v;
    BinaryTree left;
    BinaryTree right;

    public void add(Integer v) {
        if (this.v == null) {
            this.v = v;
            return;
        }
        if (this.v > v) {
            if (left == null) {
                left = new BinaryTree();
            }
            left.add(v);
        } else {
            if (right == null) {
                right = new BinaryTree();
            }
            right.add(v);
        }
    }

    public void get(Integer v) {
        if (this.v > v) {
            //当前节点大, 左右都要对比
            System.out.println(this.v);
            if (left != null) {
                left.get(v);
            }
            if (right != null) {
                right.get(v);
            }
        } else {
            //当前节点小, 只对比右节点
            if (right != null) {
                right.get(v);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree node = new BinaryTree();
        node.add(8);
        node.add(3);
        node.add(4);
        node.add(1);
        node.add(2);
        node.add(5);
        node.add(6);
        node.add(7);
        node.add(10);
        node.add(9);
        System.out.println(JSONObject.toJSONString(node));
        //大于4的
        node.get(4);
    }
}