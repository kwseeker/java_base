package top.kwseeker.classSources;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class XMLClassSourceMap implements IClassSourceMap {

    private Set<ClassSourceInfo> sourcesSet = new HashSet<>();

    // 扫描配置文件获取所有需要监视的路径和文件
    public Set<ClassSourceInfo> scanClassSources(String configFilePath) {
        System.out.println("Configuration file path: " + configFilePath);
        File file = new File(configFilePath);
        XStream xStream = getSourcesXStreamDefine();

        ClassSourceInfoSet sourceInfoSet = (ClassSourceInfoSet)xStream.fromXML(file);
        if(sourceInfoSet.getSourcesSet() != null) {
            this.sourcesSet.addAll(sourceInfoSet.getSourcesSet());
        }
        return sourcesSet;
    }

    private XStream getSourcesXStreamDefine(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("sources", ClassSourceInfoSet.class);
        xstream.alias("source", ClassSourceInfo.class);
        xstream.aliasField("sourceType", ClassSourceInfo.class, "sourceType");
        xstream.aliasField("path", ClassSourceInfo.class, "path");
        xstream.aliasField("name", ClassSourceInfo.class, "name");
        xstream.addImplicitCollection(ClassSourceInfoSet.class, "sourcesSet");
        return xstream;
    }
}
