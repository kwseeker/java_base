package service.impl;

import service.IHelloService;

public class HelloServiceImpl implements IHelloService {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

}
