import use_enum.enum3.Meal;
import use_enum.enum4.LightTest;

public class Main {

    public static void main(String[] args) {

        /*==================================================================*/
        for (int i = 0; i < 1; i++) {
            for (Meal meal : Meal.values()) {   // values方法是枚举的所有值的集合
                Meal.Food food = meal.randomSelection();
                System.out.println(food);
            }
        }
        System.out.println("--------------");

        System.out.println("演示枚举类型的遍历 >>>>>>>");
        LightTest.testTravesalEnum();
        System.out.println("演示EnumMap对象的使用和遍历 >>>>>>>");
        LightTest.testEnumMap();
        System.out.println("演示EnumSet对象的使用和遍历 >>>>>>>");
        LightTest.testEnumSet();
        
        /*==================================================================*/

    }
}
