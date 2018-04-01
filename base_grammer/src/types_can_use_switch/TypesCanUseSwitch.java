package types_can_use_switch;

import use_enum.enum2.Color;

public class TypesCanUseSwitch {

    private Color color = Color.BLANK;

    public void TestTypesCanUseSwitch() {
        System.out.println("测试可以用switch的类型：enum");
        switch (color) {
            case RED:
                System.out.println("设置的默认颜色：" + Color.RED.getName());
                break;
            case BLANK:
            case GREEN:
            case YELLO:
                System.out.println("设置的默认颜色：不是红色");
                break;
            default:
        }
    }
}
