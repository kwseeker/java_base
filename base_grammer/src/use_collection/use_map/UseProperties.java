package use_collection.use_map;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Properties --> HashTable --> Map
 *                          --> Dictionary
 *
 * Properties 可以从 Reader InputStream XML文件 中加载键值对
 * 还可以将键值对写到 Writer OutputStream XML文件 中
 *
 * 如果想要从其他文件中读取键值对，也可以先将文件转化为 InputStream 或 Reader
 * 这两个类的派生类详细看 有道云笔记 《Java IO系统.md》
 * Reader
 *      BufferedReader      LineNumberReader
 *      CharArrayReader
 *      InputStreamReader   FileReader
 *      FilterReader        PushbackReader
 *      PipedReader
 *      StringReader
 * InputStream
 *      FileInputStream
 *      PipedInputStream
 *      FilterInputStream       LineNumberInputStream | DataInputStream | BufferedInputStream | PushbackInputStream
 *      ByteArrayInputStream
 *      SequenceInputStream
 *      StringBufferInputStream
 *      ObjectInputStream
 */
public class UseProperties {

    public static void main(String[] args) {

        Properties properties = new Properties();

        //首先创建 Reader InputStream XML文件
        // 1 使用 FileReader 从文件中加载数据
        try {
            // FileReader -> InputStreamReader -> InputStream
//            InputStreamReader isReader = new FileReader("src/use_collection/use_map/properties_test_files/source.properties");
//            properties.load(isReader);
            //解决中文内容乱码问题
            InputStream is = new BufferedInputStream(new FileInputStream("src/use_collection/use_map/properties_test_files/source.properties"));
//            properties.load(new InputStreamReader(is, "utf-8"));
            properties.load(new InputStreamReader(is, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2 从 XML文件 中加载数据, XML格式需要特殊处理
        try {
//            InputStream is = UseProperties.class.getResourceAsStream("src/use_collection/use_map/properties_test_files/source.xml");
            FileInputStream is = new FileInputStream(new File("src/use_collection/use_map/properties_test_files/source.xml"));
            properties.loadFromXML(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<?> pn = properties.propertyNames();
        StringBuilder sb = new StringBuilder();
        while(pn.hasMoreElements()) {
            Object object = pn.nextElement();
            if(object instanceof String){
                String propertyKey = (String) object;
                sb.append(propertyKey);
                sb.append(":");
                //键左右的空格和值左侧会自动删除，值右侧的空格不会自动删除,手动删除
                sb.append(properties.getProperty(propertyKey).trim());
                sb.append("\n");
            }
        }
        System.out.println(sb);

        // 3 写到target.xml中
        try {
            FileOutputStream targetXML = new FileOutputStream(new File("src/use_collection/use_map/properties_test_files/target.xml"));
            properties.storeToXML(targetXML, "用户列表");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3 写到target.properties中
        try {
            FileOutputStream targetProperties = new FileOutputStream(new File("src/use_collection/use_map/properties_test_files/target.properties"));
//            properties.store(targetProperties, "用户列表");
            properties.store(new OutputStreamWriter(targetProperties, "utf-8"),"用户列表"); //使用UTF-8，解决中文乱码
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
