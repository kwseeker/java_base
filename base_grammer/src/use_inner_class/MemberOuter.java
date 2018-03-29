package use_inner_class;

/**
 * 成员内部类
 */
public class MemberOuter {

    private String s1 = "Outer InstanceMar";
    private String s2 = "OuterStatic s2";
    public void setS1(String s1) {
        this.s1 = s1;
        new MemberOuter().new MemberInner();
        this.new MemberInner();  //此时MemberOuter已经实例化完成,所以可以使用this
        new MemberInner().test2();
    }
    public static void  test2 (){
        new MemberOuter().new MemberInner();
        /*this.new MemberInner();
         * 此时MemberOuter没有实例化完成,所以不可以使用this
         * static 是在MemberOuter构造器前使用,所以此时this不能使用
         *
         */
    }

    class MemberInner{
        String s1= "Inner  instanceMae  ";
        static final String s4 = "static final  MemberInner";

        void test2(){
            System.out.println(" s1 =" + s1);
            System.out.println(" Outter MemberOuter.this.s1 =" + MemberOuter.this.s1);
            System.out.println("s2 = "+s2);
        }
    }

}
