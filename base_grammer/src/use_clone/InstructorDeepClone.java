package use_clone;

/**
 * 这个类提供clone()方法，用于在Student类对此InstructorDeepClone成员变量进行深拷贝（获取不同的成员对象）。
 */
public class InstructorDeepClone implements Cloneable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Professor [name=" + name + ", age=" + age + "]";
    }

    /**
     * 因为继承Object的clone()是protected方法外部无法直接访问，这里必须覆写为public
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

