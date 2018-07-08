package top.kwseeker.alterationObeserver;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import top.kwseeker.AgentManager;
import top.kwseeker.classSources.ClassSourceInfo;
import top.kwseeker.classSources.XMLClassSourceMap;
import top.kwseeker.utils.ConfigFileSetting;
import top.kwseeker.utils.SourceType;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用apache commons VFS 虚拟文件系统实现的监视器
 */
public class SourceAlterationObserver2Impl implements ISourceAlterationObserver {

    private FileSystemManager fileSystemManager;
    private DefaultFileMonitor fileMonitor;
    private FileListener fileListener = new FileChangeListener();

//    private Set<ClassSourceInfo> classToLoad = new HashSet<>();

    @Override
    public void initMonitor(Set<ClassSourceInfo> sourceInfoSet) {
        try {
            fileSystemManager = VFS.getManager();
            fileMonitor = new DefaultFileMonitor(fileListener);
            fileMonitor.setRecursive(true);
            restartMonitor(sourceInfoSet);
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }

    private void restartMonitor(Set<ClassSourceInfo> sourceInfoSet) {
        try {
            for(ClassSourceInfo classSourceInfo : sourceInfoSet) {
                File sourceFile = new File(classSourceInfo.getPath());
                FileObject monitoredDir = fileSystemManager.resolveFile(sourceFile.getAbsolutePath());
                fileMonitor.addFile(monitoredDir);
            }
            fileMonitor.start();
        } catch (NullPointerException | FileSystemException e) {
            e.printStackTrace();
        }
    }

    class FileChangeListener implements FileListener {

        @Override
        public void fileCreated(FileChangeEvent event) throws Exception {
            System.out.println("file " + event.getFile().getName().getBaseName() + " created...");
        }

        @Override
        public void fileDeleted(FileChangeEvent event) throws Exception {
            System.out.println("file " + event.getFile().getName().getBaseName() + " deleted...");
        }

        @Override
        public void fileChanged(FileChangeEvent event) throws Exception {
            String changedFileName = event.getFile().getName().getBaseName();
            String changedFilePath = event.getFile().getName().getPathDecoded();

            System.out.println("file " + changedFilePath + " changed...");

            //.xml 文件被修改的话
            if(StringUtils.endsWithIgnoreCase(changedFileName, SourceType.XML.getSuffix())) {
                Set<ClassSourceInfo> sourceInfoSet = new XMLClassSourceMap().scanClassSources(
                        ConfigFileSetting.CONFIGFILEPATH + ConfigFileSetting.CONFIGFILENAME);
                restartMonitor(sourceInfoSet);
                return;
            }

            //.class 文件被修改的话
            if(StringUtils.endsWithIgnoreCase(changedFileName, SourceType.CLASS.getSuffix())) {
                String classPath = StringUtils.substringBetween(changedFilePath, "classes/", ".class");
                classPath = StringUtils.replaceChars(classPath, "/", ".");
                AgentManager.getInstance().loadSourceClass(classPath);
            }

            //TODO: YAML JAR
        }
    }



}
