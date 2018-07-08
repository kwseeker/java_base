package top.kwseeker.alterationObeserver;

import org.apache.commons.io.filefilter.IOFileFilter;
import top.kwseeker.classSources.ClassSourceInfo;

import java.util.Set;

public interface ISourceAlterationObserver {

    void initMonitor(Set<ClassSourceInfo> sourceInfoSet);
}
