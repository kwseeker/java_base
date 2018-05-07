package super_class;

public class Father {
    private int a;

    public Father() {
        System.out.println("Father class is initial");
    }

    public Father(int a) {
        this.a = a;
        System.out.println("Father class is initial with " + a);
    }

    public int getA() {
        return a;
    }
}
