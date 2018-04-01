package use_callback;

/**
 * 应用端实现回调接口
 */
public class AppClient {

    public static class AppClientOne implements CallBackInterface {

        private String color;

        public AppClientOne(String color) {
            this.color = color;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public void execute(String color) {
            try {
                System.out.println("需求一直在变： 这里模拟渲染前端界面为" + color);
                Thread.sleep(2000);
                System.out.println("渲染完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void appRun() {
            FrameworkService frameworkService = new FrameworkService();
            frameworkService.setCallBackInterface(this);
            frameworkService.syncDrawPage(false);
        }
    }
}
