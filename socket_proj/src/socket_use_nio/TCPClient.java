package socket_use_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TCPClient {

    //信道选择器
    private Selector selector;
    // 与服务器通信的信道
    SocketChannel socketChannel;
    // 要连接的服务器Ip地址
    private String hostIp;
    // 要连接的远程服务器在监听的端口
    private int hostListenningPort;

    /**
     * 构造函数
     *
     * @param HostIp
     * @param HostListenningPort
     * @throws IOException
     */
    public TCPClient(String HostIp, int HostListenningPort) throws IOException {
        this.hostIp = HostIp;
        this.hostListenningPort = HostListenningPort;

        initialize();
    }

    /**
     * 初始化
     *
     * @throws IOException
     */
    private void initialize() throws IOException {
        // 打开监听信道并设置为非阻塞模式
        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp, hostListenningPort));
        socketChannel.configureBlocking(false);

        // 打开并注册选择器到信道
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 启动读取线程
        new TCPClientReadThread(selector);
    }

    /**
     * 发送字符串到服务器
     *
     * @param message
     * @throws IOException
     */
    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-16"));

        int r = socketChannel.write(writeBuffer);
        System.out.println("write return:" + r);
        //socketChannel.
    }

    public static void main(String[] args) throws IOException {
        TCPClient client = new TCPClient("127.0.0.1", 1978);
        for(int i=0; i<10; i++){
            client.sendMsg("Nio" + i);
			/*try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        }

        /*class MyThread extends Thread{
            int i;
            TCPClient client ;

            public MyThread(TCPClient client, int index) {
                i = index;
                this.client = client;
            }

            @Override
            public void run() {
                try {
                    client.sendMsg("Nio" + i);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        TCPClient client = new TCPClient("10.10.24.67", 1978);
        for(int i=0; i<10; i++){
            new MyThread(client, i).start();

        }*/

    }
}
