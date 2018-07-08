package top.kwseeker.utils;

public enum SourceType {
    CLASS(".class"),
    JAR(".jar"),
    XML(".xml"),
    YAML(".yml");

    private String suffix;

    SourceType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
