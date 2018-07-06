package primary_data_type;

public class MainTest {
    public static void main(String[] args) {
        boolean a = true;
        byte b = 0x11;
        char c = 'a';
        short d0 = -0x7fff;
        short d1 = 0x7fff;
        short d2 = 2;
        int d3 = 3;
//        short d4 = d3;
//        d2 = d2 + 1;
        int e = 11;
        long f = 233435;
//        e = f;
        f=e;
        float g = 1.2255F;
        double h = 1.22343523354533;
        System.out.println("d0: " + String.valueOf(d0) + " d2: " + String.valueOf(d2));

        //各种数据类型长度
        System.out.println("长度(Bytes): byte:" + Byte.BYTES + " char:" + Character.BYTES
                + " short: " + Short.BYTES + " int:" + Integer.BYTES + " long: " + Long.BYTES
                + " float:" + Float.BYTES + " double: " + Double.BYTES);


    }
}
