package top.kwseeker.alterationObeserver;

import top.kwseeker.classSources.ClassSourceInfo;

import java.util.Set;

//import org.apache.commons.io.filefilter.IOFileFilter;
//import org.apache.commons.io.monitor.FileAlterationListener;
//import org.apache.commons.io.monitor.FileAlterationObserver;
//
//import java.io.File;
//import java.net.URI;
//import java.util.List;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * 资源改变监听器实现
// *
// * 为每个路径创建一个观测器,监听文件改变事件
// *
// */
public class SourceAlterationObserverImpl implements ISourceAlterationObserver {

    public void initMonitor(Set<ClassSourceInfo> sourceInfoSet) {

    }
//
//    private final List<FileAlterationObserver> observers = new CopyOnWriteArrayList<FileAlterationObserver>();
//    private final SourceListener sourceListener = new SourceListener();
//    private final Queue<URI> alterationSource = new ConcurrentLinkedQueue<URI>();  //改变的资源的路径
//
//    //添加对目录及文件的监听
//    public void monitorDir(String dirPath, IOFileFilter fileFilter) throws Exception {
//        FileAlterationObserver observer = new FileAlterationObserver(dirPath, fileFilter);
//        observer.initialize();
//        observer.addListener(sourceListener);
//        observers.add(observer);
//    }
//
//    public void initMonitor() {
//        checkSourceChange();
//    }
//    //启动观测器（启动内部所有的文件监听器）
//
//    public void checkSourceChange() {
//        for(FileAlterationObserver observer : observers) {
//            observer.checkAndNotify();
//        }
//    }
//
//    class SourceListener implements FileAlterationListener {
//
//        public void onStart(FileAlterationObserver fileAlterationObserver) {
//        }
//
//        public void onDirectoryCreate(File file) {
//        }
//
//        public void onDirectoryChange(File file) {
//        }
//
//        public void onDirectoryDelete(File file) {
//        }
//
//        public void onFileCreate(File file) {
//            System.out.println("file " + file.getName() + "has been created...");
//        }
//
//        public void onFileChange(File file) {
//            System.out.println("file " + file.getName() + "has been changed...");
//        }
//
//        public void onFileDelete(File file) {
//            System.out.println("file " + file.getName() + "has been deleted...");
//        }
//
//        public void onStop(FileAlterationObserver fileAlterationObserver) {
//        }
//    }
//
//    public Queue<URI> getAlterationSource() {
//        return alterationSource;
//    }
}
