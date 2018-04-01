package use_callback;

/**
 * 应用端实现回调接口
 */
public class AppClient {

    public static class AppClientOne implements CallBackInterface {
        @Override
        public void execute() {
            System.out.println("需求一直在变： 这里模拟渲染前端界面为红色");
        }

        public void appRun() {
            FrameworkService frameworkService = new FrameworkService();
            frameworkService.setCallBackInterface(new AppClientOne());
            frameworkService.drawPage();
        }
    }

    public static class AppClientTwo implements CallBackInterface {
        @Override
        public void execute() {
            System.out.println("需求一直在变： 这里模拟渲染前端界面为绿色");
        }

        public void appRun() {
            FrameworkService frameworkService = new FrameworkService();
            frameworkService.setCallBackInterface(new AppClientTwo());
            frameworkService.drawPage();
        }
    }

    public static class AppClientThree implements CallBackInterface {
        @Override
        public void execute() {
            System.out.println("需求一直在变： 这里模拟渲染前端界面为蓝色");
        }

        public void appRun() {
            FrameworkService frameworkService = new FrameworkService();
            frameworkService.setCallBackInterface(new AppClientThree());
            frameworkService.drawPage();
        }
    }

}
