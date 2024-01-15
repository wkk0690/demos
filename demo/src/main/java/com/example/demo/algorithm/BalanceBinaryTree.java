package com.example.demo.algorithm;

/**
 * 创建节点
 */
public class BalanceBinaryTree {

    public static void main(String[] args) {
        BalanceBinaryTree node = new BalanceBinaryTree(5);
        node.add(new BalanceBinaryTree(4));
        node.add(new BalanceBinaryTree(6));
        node.add(new BalanceBinaryTree(7));
        node.add(new BalanceBinaryTree(8));
        node.add(new BalanceBinaryTree(9));
    }

    int value;
 
    BalanceBinaryTree left;
 
    BalanceBinaryTree right;
 
    public BalanceBinaryTree(int value) {
        this.value = value;
    }
 
    /**
     * 左旋转
     */
    private void leftRotate() {
        //    创建新的节点，以当前根节点的值
        BalanceBinaryTree newNode = new BalanceBinaryTree(value);
        //    把新的节点的左子树，设置成为当前节点的左子树
        newNode.left = left;
        //    把新的节点的右子树设置成，复制节点的右子树的左子树
        newNode.right = right.left;
        //    把当前节点的值替换成右子节点的值
        value = right.value;
        //    把当前节点的右子树，设置为右子树的右子树
        right = right.right;
        //    吧当前节点的左子树设置新的节点
        left = newNode;
    }
 
    /**
     * 有旋转
     */
    private void rightRotate() {
        BalanceBinaryTree newNode = new BalanceBinaryTree(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
 
    /**
     * 返回左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left != null) {
            return left.height();
        } else {
            return 0;
        }
    }
 
    /**
     * 返回右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }
 
    /**
     * 返回当前节点的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
 
    /**
     * 添加节点
     *
     * @param node
     */
    public void add(BalanceBinaryTree node) {
        if (node == null) {
            return;
        }
        //    判断传入节点的值，和当前子树根节点的关系
        if (node.value < this.value) {
            if (this.left != null) {
                //左子树递归
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                //右子树递归
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
        //    当添加完一个节点后,右子树高度-左子树高度>1
        if (rightHeight() - leftHeight() > 1) {
            //当右子树的左子树高度大于它的右子树的高度
            if(right!=null&&right.leftHeight()>right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        //左子树高度>右子树高度
        if (leftHeight() - rightHeight() > 1) {
            //当左子树的右子树高度大于它的左子树高度
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
        }
    }
 
    /**
     * 查找要删除的节点的值
     *
     * @param value 希望删除的值
     * @return 返回该节点，默认返回null
     */
    public BalanceBinaryTree search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //查找的值小于当前节点的值
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //查找的值大于当前节点的值
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }
 
    /**
     * 查找要删除的父节点
     *
     * @param value
     * @return
     */
    public BalanceBinaryTree searchParent(int value) {
        //    如果当前节点就是要删除的节点的父节点，直接返回
        if ((this.right != null && this.right.value == value) ||
                (this.left != null && this.left.value == value)) {
            return this;
        } else {
            // 向左子树递归
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
 
    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}