package com.crc.demo;

interface ProxyInte{

    void add();//新增员工

}

public class ProxyTest {

    public static void main(String[] args) {

        new MyEmpProxy(new Emp()).add();


    }
}

class Emp implements ProxyInte{

    @Override
    public void add() {
        System.out.println("新增员工");
    }
}

class MyEmpProxy implements ProxyInte{
    private ProxyInte proxyInte ;

    public MyEmpProxy(ProxyInte proxyInte) {
        this.proxyInte = proxyInte;
    }


    @Override
    public void add() {
        before();
        proxyInte.add();
        alter();
    }
    public void before(){
        System.out.println("新增员工前操作");
    }
    public void alter(){
        System.out.println("新增员工后操作");
    }
}
