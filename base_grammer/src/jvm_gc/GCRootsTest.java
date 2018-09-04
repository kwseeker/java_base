package jvm_gc;

public class GCRootsTest {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        method01();
        System.out.println("返回main方法");
        System.gc();
        System.out.println("第二次GC完成");
    }

    public static void method01() {
        System.gc();
        System.out.println("显示代码刚运行时内存信息");
        GCRootsTest t = new GCRootsTest();
        System.gc();
        System.out.println("第一次GC完成");
    }
}

/*
2018-09-02T19:22:23.040+0800: [GC (System.gc()) [PSYoungGen: 3283K->832K(37888K)] 3283K->840K(123904K), 0.0007916 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2018-09-02T19:22:23.041+0800: [Full GC (System.gc()) [PSYoungGen: 832K->0K(37888K)] [ParOldGen: 8K->600K(86016K)] 840K->600K(123904K), [Metaspace: 3515K->3515K(1056768K)], 0.0033762 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
显示代码刚运行时内存信息
2018-09-02T19:22:23.065+0800: [GC (System.gc()) [PSYoungGen: 1310K->224K(37888K)] 83830K->82744K(123904K), 0.0006774 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2018-09-02T19:22:23.065+0800: [Full GC (System.gc()) [PSYoungGen: 224K->0K(37888K)] [ParOldGen: 82520K->82544K(86016K)] 82744K->82544K(123904K), [Metaspace: 3598K->3598K(1056768K)], 0.0188062 secs] [Times: user=0.11 sys=0.00, real=0.02 secs]
第一次GC完成
返回main方法
2018-09-02T19:22:23.084+0800: [GC (System.gc()) [PSYoungGen: 1310K->128K(37888K)] 83855K->82672K(123904K), 0.0005373 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2018-09-02T19:22:23.085+0800: [Full GC (System.gc()) [PSYoungGen: 128K->0K(37888K)] [ParOldGen: 82544K->624K(86016K)] 82672K->624K(123904K), [Metaspace: 3598K->3598K(1056768K)], 0.0018556 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
第二次GC完成
Heap
 PSYoungGen      total 37888K, used 1638K [0x00000000d6100000, 0x00000000d8b00000, 0x0000000100000000)
  eden space 32768K, 5% used [0x00000000d6100000,0x00000000d6299b40,0x00000000d8100000)
  from space 5120K, 0% used [0x00000000d8100000,0x00000000d8100000,0x00000000d8600000)
  to   space 5120K, 0% used [0x00000000d8600000,0x00000000d8600000,0x00000000d8b00000)
 ParOldGen       total 86016K, used 624K [0x0000000082200000, 0x0000000087600000, 0x00000000d6100000)
  object space 86016K, 0% used [0x0000000082200000,0x000000008229c3f0,0x0000000087600000)
 Metaspace       used 3609K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 349K, capacity 388K, committed 512K, reserved 1048576K

Process finished with exit code 0
*/