package use_clone;


public class Student implements Cloneable {

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
        newStudent.instructor = (InstructorDeepClone) instructor.clone();
        return super.clone();
    }

}
