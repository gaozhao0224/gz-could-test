package com.function;

import cn.hutool.core.date.StopWatch;
import com.copy.Dog;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class MyFunctionInteTest {

}
/**
* Function函数式接口  传入T,返回R  Function<T, R>
* */
class MyFunction{

    public static void main(String[] args) {
        Dog dog = new Dog("大狗子","1");
        //传入T,返回R  Function<T, R>
        Function<Dog, String> function = new Function<Dog, String>() { //通过匿名内部类
            @Override
            public String apply(Dog o) {
                return o.getDogName();
            }
        };
        System.out.println(function.apply(dog));
        //通过 lambda表达式
        Function<Dog, String> functionLambda = (Dog)->{
            return dog.getDogSex();
        };
        System.out.println(function.apply(dog));
    }

}
/**
* Predicate断定型接口：有一个输入参数，返回值只能是 布尔值！
* */
class MyPredicate{

    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        System.out.println(predicate.test(""));
        Dog dog = new Dog("大狗");
        Predicate<Dog> predicateLambda = (Dog)->{
            return Dog.getDogName().isEmpty();
        };
        System.out.println(predicateLambda.test(dog));

    }

}
/**
* Consumer 消费型接口  只有输入 没有返回值
* */
class MyConsumer{

    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("输出："+s);
            }
        };
        consumer.accept("666");

        Consumer<String> consumerLambda = (String)->{
            System.out.println("输出："+String);
        };
        consumer.accept("777");
    }

}
/**
 * Supplier 供给型接口  没有参数 只有返回值
 * */
class MySupplier{

    public static void main(String[] args) {

        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "返回一个String类型";
            }
        };
        System.out.println(supplier.get());


        Supplier<Boolean> supplierLambda = ()->{
            return true;
        };
        System.out.println(supplierLambda.get());






    }

}
