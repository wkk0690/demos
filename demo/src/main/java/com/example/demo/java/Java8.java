package com.example.demo.java;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8新特性
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 * 1:stream() − 为集合创建串行流。
 * 2:parallelStream() − 为集合创建并行流。
 *
 *
 * https://www.jianshu.com/p/0bf8fe0f153b java8新特性
 * https://www.runoob.com/java/java8-streams.html  stream
 *
 */
public class Java8 {

    @Test
    public void demo_group() {
        List<Patient> list = new ArrayList<>();
        list.add(Patient.builder().name("111").build());
        list.add(Patient.builder().name("222").build());
        Map<String, List<Patient>> group = list.stream().collect(Collectors.groupingBy(Patient::getName));
        System.out.println(JSONObject.toJSONString(group));

        ConcurrentMap<String, List<Patient>> group2 = list.parallelStream().collect(Collectors.groupingByConcurrent(Patient::getName));
        System.out.println(JSONObject.toJSONString(group2));
    }

    @Test
    public void demo_peek() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    public void demo_4(){
        //模拟10000条数据 循环打印测试
        List<Integer> list = new ArrayList();
        for (int j = 0; j < 10000; j++) {
            list.add(j);
        }
        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 并行执行
        list.stream().forEach(integer -> {
            Thread thread = Thread.currentThread();
            // 统计并行执行list的线程
            threadSet.add(thread);
        });
        System.out.println(threadSet);
        System.out.println("threadSet一共有" + threadSet.size() + "个线程");//打印结果由此证明parallelStream是多管道线程
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");

    }

    /**
     * Runtime.getRuntime().availableProcessors() 逻辑处理器数（单个物理处理器相当于拥有两个逻辑处理器）
     */
    @Test
    public void demo_3(){
        int max = 50;
        List<String> values = new ArrayList<>(max);
        for(int i = 0; i < max; i ++){
            values.add(UUID.randomUUID().toString());
        }

        //parallelStream 共用线程池
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5"); //设置线程数
        long t0 = System.currentTimeMillis();
        values.parallelStream().map(item -> {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).collect(Collectors.toList());
        long t1 = System.currentTimeMillis();
        System.out.println(t1 - t0);

        //stream
        long t2 = System.currentTimeMillis();
        values.stream().map(item -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).collect(Collectors.toList());
        long t3 = System.currentTimeMillis();
        System.out.println(t3 - t2);


    }

    @Test
    public void demo_1(){
        //Predicate接口只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）：
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        //Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）：
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        String apply = backToString.apply("123");
        System.out.println(apply);

        //Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<User> userSupplier = User::new;
        User user = userSupplier.get();
        System.out.println(user);

        //Consumer 接口表示执行在单个参数上的操作。
        Consumer<User> greeter = u -> System.out.println("hello, " + u.getName());
        greeter.accept(new User("jack", "123"));

        //Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
        Comparator<User> comparator = (u1, u2) -> u1.getName().compareTo(u2.getName());
        User u1 = new User("jack", "123");
        User u2 = new User("rose", "456");
        int compare = comparator.compare(u1, u2);
        int compare1 = comparator.reversed().compare(u1, u2);
        System.out.println(compare);
        System.out.println(compare1);

        /**
         * Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，这是下一届中将要用到的重要概念，现在先简单的看看这个接口能干什么：
         * Optional 被定义为一个简单的容器，其值可能是null或者不是null。在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional。
         */
        String str = "bam";
        Optional<String> optional = Optional.of(str);
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));
        optional.ifPresent(s -> System.out.println(s.charAt(0)));
    }

    class User{
        private String name;
        private String pass;

        public User() {
        }

        public User(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", pass='" + pass + '\'' +
                    '}';
        }
    }

    //函数式接口
    @Test
    public void demo0(){
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        //使用 :: 关键字来传递方法或者构造函数引用
        Converter<String, Integer> converter1 = Integer::valueOf;
        Integer converted1 = converter1.convert("123");
        System.out.println(converted1);   // 123

        //也可以引用一个对象的方法：
//       converter2 = something::startsWith;
//        String converted2 = converter2.convert("Java");
//        System.out.println(converted2);    // "J"

    }

    //@FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    //Lambda 表达式
    @Test
    public void demo(){
        List<String> names = Arrays.asList("1", "3", "2");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        names = Arrays.asList("1", "3", "2");
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        System.out.println(names);

        names = Arrays.asList("1", "3", "2");
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);
    }

    @Test
    public void demo1() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        //Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
        //limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        //filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
        List<String> strList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strList.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        Random ran = new Random();
        ran.ints().limit(10).sorted().forEach(System.out::println);

        //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        List<String> strs = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count1 = strs.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println(count1);

        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> ss = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> collect = ss.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println(collect);

        //另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
        List<Integer> intList = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = intList.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }


    public static void main(String args[]){
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("列表: " +strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings,", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        System.out.println("列表: " +integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for(int i=0; i < 10; i++){
            System.out.println(random.nextInt());
        }

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " +strings);

        count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();
        
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
