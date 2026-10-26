interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    boolean state = false;
    public void on() { state = true; System.out.println("Fan ON"); }
    public void off() { state = false; System.out.println("Fan OFF"); }
}

class Light implements Switchable {
    boolean state = false;
    public void on() { state = true; System.out.println("Light ON"); }
    public void off() { state = false; System.out.println("Light OFF"); }
}

@FunctionalInterface
interface SwitchRule {
    boolean canSwitch(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};

        for (Switchable d : devices) {
            d.toggle();
        }

        SwitchRule rule1 = new SwitchRule() {
            public boolean canSwitch(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        SwitchRule rule2 = (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("Anonymous class: " + rule1.canSwitch(devices[0], 10));
        System.out.println("Lambda: " + rule2.canSwitch(devices[1], 10));
    }
}
