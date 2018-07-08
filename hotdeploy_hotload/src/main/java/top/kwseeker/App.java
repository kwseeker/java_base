package top.kwseeker;

public class App {

    public static void main( String[] args )
    {
        //TODO: 为什么要开一个线程
        Thread t = new Thread( () -> {
                AgentManager manager = AgentManager.getInstance();
                manager.init();
        });
        t.start();

        while(true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
