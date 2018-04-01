package use_callback;

/**
 * 框架或容器将这个接口注册在自己的代码中，
 * 并在适当的时候调用
 *
 * 同步回调：应用以单线程多次调用回调函数
 * 异步回调：应用每次调用回调函数都是在一个新线程中执行的
 */
public class FrameworkService {

    private CallBackInterface callBackInterface = null;

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    private void drawPage() {
        try {
            //渲染前端界面的准备工作
            System.out.println("渲染前端页面颜色准备工作");
            Thread.sleep(1000);
            System.out.println("Thread name: " + Thread.currentThread().getName());

            //调用留给前端进行个性化定制的接口
            System.out.println("选用前端个性化渲染的颜色");
            callBackInterface.execute(callBackInterface.getColor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 渲染前端界面颜色
     */
    public void syncDrawPage(boolean sync) {
        if (sync) {
            //同步回调
            drawPage();
        } else {
            //异步回调
//          new Thread(new Runnable(){
//              @Override
//              public void run() {
//                  drawPage();
//              }
//          }).start();
//            new Thread( () -> drawPage() ).start();
            new Thread( this::drawPage ).start();
        }
    }

}
