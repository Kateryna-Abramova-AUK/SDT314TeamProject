package abstractfactory.gear;

public class GearDemo {
    public static void main(String[] args) {
        new GearKit(SkillLevel.BEGINNER.gearFactory()).print("Maya");
        new GearKit(SkillLevel.ADVANCED.gearFactory()).print("Jonas");
    }
}
