package java.lang;

public class Student {


    public static void main(String[] args) {

        Student s = new Student();
        ClassLoader classLoader = s.getClass().getClassLoader();
        ClassLoader parent = s.getClass().getClassLoader().getParent();
        ClassLoader parent1 = s.getClass().getClassLoader().getParent().getParent();
        System.out.println(classLoader);
        System.out.println(parent);
        System.out.println(parent1);
        System.out.println(s);
        ClassLoader classLoaders1 = String.class.getClassLoader();
        ClassLoader parents1 = String.class.getClassLoader().getParent();
        ClassLoader parents11 = String.class.getClassLoader().getParent().getParent();
        System.out.println(classLoaders1);
        System.out.println(parents1);
        System.out.println(parents11);
    }

    @Override
    public String toString(){
        return "hello";
    }
}