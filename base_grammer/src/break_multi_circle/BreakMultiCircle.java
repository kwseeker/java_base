package break_multi_circle;

public class BreakMultiCircle {
    public void testBreakMultiCircle() {
        System.out.println("Before enter circle");
        ok:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("i=" + i + " " + "j=" + j);
                if(j == 2)
                    break ok;
            }
        }
        System.out.println("Before enter circle");
    }
}
