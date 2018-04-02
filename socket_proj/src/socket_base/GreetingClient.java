package socket_base;

import java.io.*;
import java.net.Socket;

public class GreetingClient {
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);       // 1 客户端实例化Socket对象通过指定的服务器名和端口号连接
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());

            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
