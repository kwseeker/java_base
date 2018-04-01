package use_callback;

/**
 * 框架或容器将这个接口注册在自己的代码中，
 * 并在适当的时候调用
 */
public class FrameworkService {

    private CallBackInterface callBackInterface = null;

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    /**
     * 渲染前端界面颜色
     */
    public void drawPage() {
        //渲染前端界面的准备工作
        System.out.println("渲染前端页面颜色准备工作");

        //调用留给前端进行个性化定制的接口
        System.out.println("选用前端个性化渲染的颜色");
        callBackInterface.execute();
    }
}
