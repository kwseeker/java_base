import Serializable_uid.Person;
import Serializable_uid.WhySerialversionUID;
import multi_threads_communication.NotifyOneOrAll;
import multi_threads_communication.PipeStream;
import multi_threads_communication.WaitNotifyThread1;
import multi_threads_communication.WaitNotifyThread2;
import super_class.SuperClassTest;
import use_callback.AppClient;
import use_clone.Professor;
import use_clone.InstructorDeepClone;
import use_clone.Student;
import use_multi_thread.*;
import use_multi_thread2.CallableThread;
import use_timer.TimerTest;
import access_right.MultiClass;
import access_right.Test;
import break_multi_circle.BreakMultiCircle;
import types_can_use_switch.TypesCanUseSwitch;
import use_enum.enum2.Color;
import use_enum.enum3.Meal;
import use_enum.enum4.LightTest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.math.BigInteger;
import java.util.concurrent.*;

import static Serializable_uid.WhySerialversionUID.getObjectFromFile;
import static Serializable_uid.WhySerialversionUID.objectToFile;

public class Main {

    public static void main(String[] args) {
        /*=Callable FutureTask实现线程=========================================*/
        String fileName = "crab_file";//文件名

        // 这里是把对象序列化到文件
        Person crab = new Person();
        crab.setName("Mr.Crab");

        try {
            if (1 == args.length) {
                switch (args[0]) {
                    case "serial":
                        //储存到文件中
                        WhySerialversionUID.objectToFile(crab, fileName);
                        break;
                    case "back":
                        // 这里是把对象序列化到文件
                        Person crabBack = (Person) WhySerialversionUID.getObjectFromFile(fileName);
                        System.out.println("Hi, My name is " + crabBack.getName());
                        break;
                    default:
                        System.out.println("input error");
                }
            } else {
                System.out.println("please input one param");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*=Callable FutureTask实现线程=========================================*/
//        CallableThread callableThread = new CallableThread();
//        FutureTask<Integer> futureTask = new FutureTask<>(callableThread);
//        System.out.println("Current thread: " + Thread.currentThread().getName());
//        new Thread(futureTask, "有返回值的线程").start();
//
//        try {
//            System.out.println("子线程返回值： " + futureTask.get());  //这里是阻塞获取返回值
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        /**
//         * 使用线程池
//         */
//        try {
//            int taskSize = 5;
//            ExecutorService pool = Executors.newFixedThreadPool(taskSize);
//            List<Future> list = new ArrayList<>();
//            for (int i = 0; i < taskSize; i++) {
//                Callable<Integer> callable = new CallableThread();
//                Future future = pool.submit(callable);
//                list.add(future);
//            }
//            pool.shutdown();       //等待所有线程执行完毕才会关闭
//            for (Future future : list) {
//                System.out.println("任务返回值： " + future.get());
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        /*=回调函数的意义与使用==================================================*/
//        AppClient.AppClientOne appClientOne = new AppClient.AppClientOne("红色");
//        AppClient.AppClientOne appClientTwo = new AppClient.AppClientOne("绿色");
//        AppClient.AppClientOne appClientThree = new AppClient.AppClientOne("蓝色");
//        appClientOne.appRun();
//        appClientTwo.appRun();
//        appClientThree.appRun();

        /*=使用重入读写锁ReentrantReadWriteLock=================================*/
//        ReentrantReadWriteLockTest service = new ReentrantReadWriteLockTest();
//        ReentrantReadWriteLockTest.MyThread myThread1 = new ReentrantReadWriteLockTest.MyThread(service, "read");
//        ReentrantReadWriteLockTest.MyThread myThread2 = new ReentrantReadWriteLockTest.MyThread(service, "write");
//        myThread1.start();
//        myThread2.start();

        /*=使用重入锁ReentrantLock=============================================*/
//        ReentrantLockTest service = new ReentrantLockTest();
//        ReentrantLockTest.MyThread thread1 = new ReentrantLockTest.MyThread(service);
//        ReentrantLockTest.MyThread thread2 = new ReentrantLockTest.MyThread(service);
//        ReentrantLockTest.MyThread thread3 = new ReentrantLockTest.MyThread(service);
//        thread1.start();
//        thread2.start();
//        thread3.start();

//        try {
//            ConditionWaitNotify.MyService service = new ConditionWaitNotify.MyService();
//            ConditionWaitNotify.ThreadA threadA = new ConditionWaitNotify.ThreadA(service);
//            threadA.start();
//            Thread.sleep(3000);
//            service.signal();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * 公平锁与非公平锁
         */
//        final FairAndNoFairLock fairAndNoFairLock = new FairAndNoFairLock(false);
////        Runnable runnable = new Runnable() {
////            @Override
////            public void run() {
////                System.out.println("线程" + Thread.currentThread().getName() + "运行了");
////                fairAndNoFairLock.serviceMethod();
////            }
////        };
//        Runnable runnable = () -> {
//            System.out.println("线程" + Thread.currentThread().getName() + "运行了");
//            fairAndNoFairLock.serviceMethod();
//        };
//        Thread[] threadArray = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            threadArray[i] = new Thread(runnable);
//        }
//        for (int i = 0; i < 10; i++) {
//            threadArray[i].start();
//        }

        /*=使用定时器Timer====================================================*/
//        TimerTest.MyTask myTask = new TimerTest.MyTask();
//        TimerTest.MyTask myTask1 = new TimerTest.MyTask();
//        TimerTest.MyTask myTask2 = new TimerTest.MyTask();
//
//        System.out.println("当前时间： " +  new Date());
//        Timer timer = TimerTest.getTimer();
//        timer.schedule(myTask,  2000);
//        timer.schedule(myTask1, 3000);
//
//        Date date = new Date(System.currentTimeMillis()+10000);
//        timer.schedule(myTask2, date, 2000);

        /*=线程间通信=========================================================*/
//        try {
//            Object lock = new Object();
//            WaitNotifyThread1 thread1 = new WaitNotifyThread1(lock);
//            thread1.start();
//            Thread.sleep(3000);
//            WaitNotifyThread2 thread2 = new WaitNotifyThread2(lock);
//            thread2.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        final Object lock = new Object();
//        NotifyOneOrAll.ThreadA threadA = new NotifyOneOrAll.ThreadA(lock);
//        NotifyOneOrAll.ThreadB threadB = new NotifyOneOrAll.ThreadB(lock);
//        NotifyOneOrAll.ThreadC threadC = new NotifyOneOrAll.ThreadC(lock);
//        try {
//            threadA.start();
//            threadB.start();
//            threadC.start();
//            Thread.sleep(1000);
//            NotifyOneOrAll.NotifyThread notifyThread = new NotifyOneOrAll.NotifyThread(lock);
//            notifyThread.start();
//            Thread.sleep(1000);
//            NotifyOneOrAll.NotifyAllThread notifyAllThread = new NotifyOneOrAll.NotifyAllThread(lock);
//            notifyAllThread.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        try {
//            PipedInputStream input = new PipedInputStream();
//            PipedOutputStream out = new PipedOutputStream();
//            PipeStream.WriteData writeData = new PipeStream.WriteData();
//            PipeStream.ReadData readData = new PipeStream.ReadData();
//            out.connect(input);
//
//            PipeStream.ThreadWrite threadWrite = new PipeStream.ThreadWrite(writeData, out);
//            PipeStream.ThreadRead threadRead = new PipeStream.ThreadRead(readData, input);
//            threadWrite.start();
//            Thread.sleep(1000);
//            threadRead.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*=使用枚举use_enum=================================================*/
//        for (int i = 0; i < 1; i++) {
//            for (Meal meal : Meal.values()) {   // values方法是枚举的所有值的集合
//                Meal.Food food = meal.randomSelection();
//                System.out.println(food);
//            }
//        }
//        System.out.println("--------------");
//
//        System.out.println("演示枚举类型的遍历 >>>>>>>");
//        LightTest.testTravesalEnum();
//        System.out.println("演示EnumMap对象的使用和遍历 >>>>>>>");
//        LightTest.testEnumMap();
//        System.out.println("演示EnumSet对象的使用和遍历 >>>>>>>");
//        LightTest.testEnumSet();

        /*=对象克隆==========================================================*/
//        Professor p1 = new Professor();
//        p1.setName("Professor Zhang");
//        p1.setAge(30);
//
//        InstructorDeepClone instructor = new InstructorDeepClone();
//        instructor.setName("Instructor Wang");
//        instructor.setAge(24);
//
//        Student s1 = new Student();
//        s1.setName("xiao ming");
//        s1.setAge(18);
//        s1.setProfessor(p1);
//        s1.setInstructor(instructor);
//
//        System.out.println(s1);
//
//        try {
//            Student s2 = (Student) s1.clone();
//            Professor p2 = s2.getProfessor();   //返回的Professor对象的引用，s1/s2的内部professor对象是一样的
//            InstructorDeepClone i2 = (InstructorDeepClone) s2.getInstructor().clone();
//            p2.setName("Professor Li");
//            p2.setAge(45);
//            s2.setProfessor(p2);
//            i2.setName("Instructor Zhao");
//            i2.setAge(25);
//            s2.setInstructor(i2);
//            System.out.println("复制后的：s1 = " + s1);
//            System.out.println("复制后的：s2 = " + s2);
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        /*=获取父类名字========================================================*/
//        new SuperClassTest().test();

        /*=String类型操作原理==================================================*/
//        String s = "Hello";
//        s += " world!";     //每次+操作都创建了一个StringBuffer类的可变实例
//        System.out.println(s);

        /*=使用多线程==========================================================*/
//        System.out.println("当前线程名: " + Thread.currentThread().getName());
//
//        MyThread myThread = new MyThread();
//        myThread.start();
//        System.out.println("主线程执行结束");

//        MyRunnable myRunnable = new MyRunnable();
//        Thread thread = new Thread(myRunnable);

//        Thread thread1 = new Thread(new MyRunnable());
//        Thread thread2 = new Thread(new MyRunnable());
//        Thread thread3 = new Thread(new MyRunnable());
//        Thread thread4 = new Thread(new MyRunnable());
//        Thread thread5 = new Thread(new MyRunnable());
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        System.out.println("主线程执行结束");

//        try {
//            DaemonThread thread = new DaemonThread();
//            thread.setDaemon(true);
//            thread.start();
//            Thread.sleep(5000);
//            System.out.println("我离开thread对象也不再打印了");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        ReentrantLockThread reentrantLockThread = new ReentrantLockThread();
//        reentrantLockThread.start();

//        ReentrantLockThread reentrantLockThread = new ReentrantLockThread();
//        ReentrantLockThread.SubReentrantLockTread subReentrantLockTread = reentrantLockThread.new SubReentrantLockTread();
//        subReentrantLockTread.start();

//        SyncLockObject syncLockObject = new SyncLockObject();
//        SyncLockObject.MyThread1 thread1 = syncLockObject.new MyThread1(syncLockObject);
//        SyncLockObject.MyThread2 thread2 = syncLockObject.new MyThread2(syncLockObject);
//        SyncLockObject.MyThread3 thread3 = syncLockObject.new MyThread3(syncLockObject);
//        SyncLockObject.MyThread4 thread4 = syncLockObject.new MyThread4(syncLockObject);
//        SyncLockObject.MyThread5 thread5 = syncLockObject.new MyThread5(syncLockObject);
//        SyncLockObject.MyThread6 thread6 = syncLockObject.new MyThread6(syncLockObject);
//        SyncLockObject.MyThread7 thread7 = syncLockObject.new MyThread7(syncLockObject);
//        try {
//            thread1.start();
//            Thread.sleep(200);
//            thread2.start();
//            thread3.start();
//            thread4.start();
//            thread5.start();
//            thread6.start();
//            thread7.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
        /*=access_right=====================================================*/
//        System.out.println("getBString: ");
//        new MultiClass().getStrings();
//        new Test().getStrings();

        /*=break_multi_circle===============================================*/
//        new BreakMultiCircle().testBreakMultiCircle();

        /*=types_can_use_switch=============================================*/
//        new TypesCanUseSwitch().TestTypesCanUseSwitch();

        /*=多线程===========================================================*/
//        ThreadLocalTest.ThreadA threadA = new ThreadLocalTest.ThreadA();
//        ThreadLocalTest.ThreadB threadB = new ThreadLocalTest.ThreadB();
//        threadA.start();
//        threadB.start();
    }
}
