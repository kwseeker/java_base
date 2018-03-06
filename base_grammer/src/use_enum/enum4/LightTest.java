package use_enum.enum4;

import java.util.EnumMap;
import java.util.EnumSet;

public class LightTest {

    public enum Light {
        RED(1),
        GREEN(2),
        YELLOW(3);

        private int nCode;

        private Light(int _nCode) {
            this.nCode = _nCode;
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

    public static void testTravesalEnum() {
        Light[] allLight = Light.values();
        for (Light aLight : allLight) {
            System.out.println("当前灯name:" + aLight.name() + " ordinal:" + aLight.ordinal());
            System.out.println("当前灯：" + aLight);
        }
    }

    public static void testEnumMap() {
        EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(Light.class);
        currEnumMap.put(Light.RED, "红灯");
        currEnumMap.put(Light.GREEN, "绿灯");
        currEnumMap.put(Light.YELLOW, "黄灯");

        for (Light aLight : Light.values()) {
            System.out.println("[key="+aLight.name()+", value="+currEnumMap.get(aLight)+"]");
        }
    }

    public static void testEnumSet() {
        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
        for (Light aLightSetElement : currEnumSet) {
            System.out.println("当前EnumSet中数据为："+ aLightSetElement);
        }
    }
}

