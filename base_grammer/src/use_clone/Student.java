package use_clone;

import java.io.Serializable;

/**
 * 对Professor成员进行浅拷贝（拷贝的是成员变量地址）
 * 对InstructorDeepClone成员进行深拷贝（通过成员变量地址拷贝里面的内容）
 */
public class Student implements Cloneable, Serializable {

    private String name;
    private int age;
    private Professor professor;
    private InstructorDeepClone instructor;

    public InstructorDeepClone getInstructor() {
        return instructor;
    }
    public void setInstructor(InstructorDeepClone instructor) {
        this.instructor = instructor;
    }
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
    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", professor="
                + professor + ", instructor=" + instructor +"]";
    }

    public Object clone() throws CloneNotSupportedException{
        Student newStudent = (Student)super.clone();
        newStudent.instructor = (InstructorDeepClone) instructor.clone();   //拷贝instructor成员的内容并生成一个新的对象
        return newStudent;
    }

}
