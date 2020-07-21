package com.example.demo.percent3;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1_Demo1 {

    /**
     * 题目：
     * 警察局抓了a,b,c,d四名偷窃嫌疑犯，当中只有一个是小偷，审问结果如下：
     * a说：“我不是小偷。”
     * b说：“c是小偷。”
     * c说：“小偷肯定是d。”
     * d说：“c在冤枉人。”
     * 现在已经知道4个人中3人说的是真话，一个说的是假话，那么谁是小偷？
     */
    @Test
    public void demo(){
        for(int i = 1; i <= 4; i ++){
            int a = i != 1 ? 1 : 0;
            int b = i == 3 ? 1 : 0;
            int c = i == 4 ? 1 : 0;
            int d = i != 4 ? 1 : 0;
            if(a + b + c + d == 3) {
                System.out.println(i);
            }
        }
    }

    /**
     * https://www.cnblogs.com/doudouxiaoye/p/5693434.html
     * 十进制系统中能不能准确表示出1/3呢？同样二进制系统也无法准确表示1/10
     */
    @Test
    public void demo1(){
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void demo2(){
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * switch的表达式必须是char, byte, short, int, Character, Byte, Short, Integer, String, or an enum类型, 否则会发生编译错误
     *  1: 与switch语句关联的每个case都必须和switch的表达式的类型一致。
     *  2: 如果 switch表达式是枚举类型,  case 常量也必须是枚举类型.
     *  3: 不允许同一个switch的两个case常量的值相同.
     *  4: 和switch语句关联的常量不能为null.
     *  5: 一个switch语句最多有一个default标签.
     */
    @Test
    public void demo3(){
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    /**
     *
     */
    @Test
    public void demo4() {
        //此构造函数是float或double转到BigDecimal的推荐方式，因为该构造方法不会像BigDecimal(double)一样会有一些不可预测的情况。
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        Object aa = null;
        //BigDecimal c = new BigDecimal(aa);
        System.out.println(b);
        System.out.println(1.23E3);
        System.out.println(1.23E+3);
        System.out.println(1.23E-3); //科学计数法, 即12*10^(-3)
    }


    /**
     * https://www.cnblogs.com/takumicx/p/9338983.html
     *
     * 问题： ReentrantLock和synchronized的相同点
     * 1.ReentrantLock和synchronized都是独占锁,只允许线程互斥的访问临界区。但是实现上两者不同:synchronized加锁解锁的过程是隐式的,
     * 用户不用手动操作,优点是操作简单，但显得不够灵活。一般并发场景使用synchronized的就够了；ReentrantLock需要手动加锁和解锁,
     * 且解锁的操作尽量要放在finally代码块中,保证线程正确释放锁。ReentrantLock操作较为复杂，但是因为可以手动控制加锁和解锁过程,
     * 在复杂的并发场景中能派上用场。
     *
     * 2.ReentrantLock和synchronized都是可重入的。synchronized因为可重入因此可以放在被递归执行的方法上,且不用担心线程最后能否正确释放锁；
     * 而ReentrantLock在重入时要却确保重复获取锁的次数必须和重复释放锁的次数一样，否则可能导致其他线程无法获得该锁。
     *
     * 问题： ReentrantLock相比synchronized的额外功能
     *  公平锁是指当锁可用时,在锁上等待时间最长的线程将获得锁的使用权。而非公平锁则随机分配这种使用权。
     *  和synchronized一样，默认的ReentrantLock实现是非公平锁,因为相比公平锁，非公平锁性能更好。
     *  当然公平锁能防止饥饿,某些情况下也很有用。在创建ReentrantLock的时候通过传进参数true创建公平锁,
     *  如果传入的是false或没传参数则创建的是非公平锁
     *  ReentrantLock lock = new ReentrantLock(true);
     */
    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lock.tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
