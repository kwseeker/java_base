package top.kwseeker.alterationObeserver;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.junit.Test;

import java.io.File;

public class ListenerTest {

    @Test
    public void test() {
        try {
            FileSystemManager fsm = VFS.getManager();/////////////
            DefaultFileMonitor fileMonitor = new DefaultFileMonitor(new FileListener() {
                @Override
                public void fileCreated(FileChangeEvent event) throws Exception {
                    resolveEvent("Created", event);
                }

                @Override
                public void fileDeleted(FileChangeEvent event) throws Exception {
                    resolveEvent("Deleted", event);
                }

                @Override
                public void fileChanged(FileChangeEvent event) throws Exception {
                    resolveEvent("Changed", event);
                }

                private void resolveEvent(String type, FileChangeEvent event) {
                    FileObject fileObject = event.getFile();
                    FileName fileName = fileObject.getName();
                    System.out.println(type + ": " + fileName.toString());
                }
            });
//            fileMonitor.stop();           //前面不能加 stop()
            FileObject file = fsm.resolveFile(new File("G://vfs").getAbsolutePath());
            FileObject file2 = fsm.resolveFile(new File("G://vfs2").getAbsolutePath());
            fileMonitor.addFile(file);
            fileMonitor.addFile(file2);
            fileMonitor.start();
            Thread.sleep(50000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
