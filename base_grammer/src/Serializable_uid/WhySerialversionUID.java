package Serializable_uid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WhySerialversionUID {

    public static void objectToFile(Object obj,String fileName) throws Exception{
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(fileName));
        oo.writeObject(obj);
        oo.close();
    }

    public static Object getObjectFromFile(String fileName) throws Exception {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(
                fileName));
        Person crab_back = (Person) oi.readObject();
        oi.close();
        return crab_back;
    }
}
