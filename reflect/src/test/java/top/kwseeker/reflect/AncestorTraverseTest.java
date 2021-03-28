package top.kwseeker.reflect;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class AncestorTraverseTest {

    @Test
    public void testTraverseAncestor() {
        AncestorTraverser traverse = new AncestorTraverser(ConcurrentHashMap.class);
        traverse.traverseAncestor();
    }
}