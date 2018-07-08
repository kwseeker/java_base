package top.kwseeker;

import top.kwseeker.alterationObeserver.ISourceAlterationObserver;
import top.kwseeker.alterationObeserver.SourceAlterationObserver2Impl;
import top.kwseeker.classFactory.TestApp;
import top.kwseeker.classLoader.HotClassLoader;
import top.kwseeker.classLoader.IClassLoader;
import top.kwseeker.classSources.ClassSourceInfo;
import top.kwseeker.classSources.IClassSourceMap;
import top.kwseeker.classSources.XMLClassSourceMap;
import top.kwseeker.utils.ConfigFileSetting;

import java.util.HashSet;
import java.util.Set;

public class AgentManager {

    private static AgentManager agentManager;   //单例对象

    private IClassSourceMap iClassSourceMap = new XMLClassSourceMap();
    private IClassLoader iClassLoader = new HotClassLoader();
    private ISourceAlterationObserver iSourceAlterationObserver = new SourceAlterationObserver2Impl();

    private Set<ClassSourceInfo> sourcesInfoSet = new HashSet<>();

    public void init() {
        //获取需要监测资源的集合
        String configFile = ConfigFileSetting.CONFIGFILEPATH + ConfigFileSetting.CONFIGFILENAME;
        sourcesInfoSet = iClassSourceMap.scanClassSources(configFile);

        //启动监听器
        iSourceAlterationObserver.initMonitor(sourcesInfoSet);

        new TestApp().runApp();
    }


    // 监测到资源文件改变调用类加载器重新加载
    public void loadSourceClass(String changedFile){
        ClassLoader loader = this.iClassLoader.createClassLoader(getClass().getClassLoader());
        System.out.println("类加载器：" + loader);
        try {
            Class<?> testAppClass = loader.loadClass(changedFile);      // ???
            System.out.println("类：" + testAppClass);
            TestApp testApp = (TestApp) testAppClass.newInstance();
            testApp.runApp();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AgentManager getInstance() {
        if(agentManager == null){
            agentManager = new AgentManager();
        }
        return agentManager;
    }
}
