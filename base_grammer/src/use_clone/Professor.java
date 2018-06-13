package use_clone;

/**
 * 此对象不支持Clone, 在Student中对Professor类型的成员只能进行浅拷贝（只能获取同一成员对象的引用）
 */
public class Professor {

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

}