package top.kwseeker.classSources;

import java.util.Set;

/**
 * 配置文件 yml 到 Set<ClassSourceInfo> 的映射接口
 */
public interface IClassSourceMap {

    // 扫描配置文件获取所有需要监视的路径和文件
    Set<ClassSourceInfo> scanClassSources(String configFilePath);

}
