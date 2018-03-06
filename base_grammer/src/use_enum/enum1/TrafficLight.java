package use_enum.enum1;

public class TrafficLight {

    private Signal color = Signal.RED;

    public void change() {
        switch (color) {
            case RED:
                break;
            case GREEN:
                break;
            case YELLOW:
                break;
        }
    }

}
