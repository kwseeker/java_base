import proxy.ServiceProxyCglib;
import proxy.ServiceProxyJdk;
import service.IHelloService;
import service.impl.HelloServiceImpl;

public class Main {

    public static void main(String[] args) {
        ServiceProxyJdk serviceProxyJdk = new ServiceProxyJdk();
        IHelloService proxyJdk = (IHelloService) serviceProxyJdk.bind(new HelloServiceImpl());
        proxyJdk.sayHello("Arvin");

        ServiceProxyCglib serviceProxyCglib = new ServiceProxyCglib();
        IHelloService proxyCglib = (IHelloService) serviceProxyCglib.getInstance(new HelloServiceImpl());
        proxyCglib.sayHello("Arvin");
    }

}
