package java.lang1;

/*
* 双亲委派机制 执行最上面的
* 先是当前应用类加载器 getClass().getClassLoader()
* 然后扩展类加载器 在Java\jre1.8.0_144\lib\ext下ext里
* 最后是在根记载器下  Java\jre1.8.0_144\lib\rz.jar
* */
public class String1 {


    public static void main(String[] args) {

        String1 s = new String1();
        ClassLoader classLoader = s.getClass().getClassLoader();
        ClassLoader parent = s.getClass().getClassLoader().getParent();
        ClassLoader parent1 = parent.getParent();
        System.out.println(classLoader);
        System.out.println(parent);
        System.out.println(parent1);
        System.out.println(s);

    }

    @Override
    public String toString(){
        return "hello";
    }
}