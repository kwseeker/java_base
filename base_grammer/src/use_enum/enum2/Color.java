package use_enum.enum2;

public enum Color implements Behaviour {
    RED("红色", 1),
    GREEN("绿色", 2),
    BLANK("黑色", 3),
    YELLO("黄色", 4);

    private String name;
    private int index;

    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 添加新的方法
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // 实现接口方法
    @Override
    public String getInfo() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 覆盖枚举继承的方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }
}
