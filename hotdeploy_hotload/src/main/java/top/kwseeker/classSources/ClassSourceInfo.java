package top.kwseeker.classSources;

import top.kwseeker.utils.SourceType;

/**
 * .class .jar .yml 等资源定位信息（类型，路径,文件名）
 */
public class ClassSourceInfo {

    private SourceType sourceType;
    private String path = "";
    private String name = "";

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
