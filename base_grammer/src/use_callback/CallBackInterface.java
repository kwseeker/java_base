package use_callback;

/**
 * 优秀的框架或容器提供了大量的回调接口，应对框架或容器无法满足的功能或进行功能个性化定制。
 * 这一设计允许了底层代码调用高层定义的子程序，增强程序灵活性。
 *
 * 比如有个渲染前端界面颜色的功能，为了保留灵活性，框架只是提供个接口，做必要操作，具体
 * 渲染什么颜色交由前端自己实现。
 */
public interface CallBackInterface {
    String getColor();
    void execute(String color);
}
