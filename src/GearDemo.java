interface SurfBoard { String describe(); }
interface Wetsuit   { String describe(); }
interface Leash     { String describe(); }

interface GearFactory {
    SurfBoard createBoard();
    Wetsuit createWetsuit();
    Leash createLeash();
}

final class FoamLongboard implements SurfBoard {
    public String describe() { return "9 ft foam soft-top longboard (stable, forgiving)"; }
}
final class PaddedWetsuit implements Wetsuit {
    public String describe() { return "4/3 mm full suit with extra padding"; }
}
final class CoiledLeash implements Leash {
    public String describe() { return "9 ft coiled ankle leash (stays out of the way)"; }
}
final class BeginnerGearFactory implements GearFactory {
    public SurfBoard createBoard()   { return new FoamLongboard(); }
    public Wetsuit createWetsuit()   { return new PaddedWetsuit(); }
    public Leash createLeash()       { return new CoiledLeash(); }
}

final class PerformanceShortboard implements SurfBoard {
    public String describe() { return "6 ft performance shortboard (fast, responsive)"; }
}
final class FlexWetsuit implements Wetsuit {
    public String describe() { return "3/2 mm stretch full suit"; }
}
final class StraightLeash implements Leash {
    public String describe() { return "6 ft straight performance leash"; }
}
final class AdvancedGearFactory implements GearFactory {
    public SurfBoard createBoard()   { return new PerformanceShortboard(); }
    public Wetsuit createWetsuit()   { return new FlexWetsuit(); }
    public Leash createLeash()       { return new StraightLeash(); }
}

enum SkillLevel {
    BEGINNER, ADVANCED;

    GearFactory gearFactory() {
        return switch (this) {
            case BEGINNER -> new BeginnerGearFactory();
            case ADVANCED -> new AdvancedGearFactory();
        };
    }
}

final class GearKit {
    private final SurfBoard board;
    private final Wetsuit wetsuit;
    private final Leash leash;

    GearKit(GearFactory factory) {
        this.board = factory.createBoard();
        this.wetsuit = factory.createWetsuit();
        this.leash = factory.createLeash();
    }

    void print(String customer) {
        System.out.println(customer + "'s kit:");
        System.out.println("  - " + board.describe());
        System.out.println("  - " + wetsuit.describe());
        System.out.println("  - " + leash.describe());
    }
}

public class GearDemo {
    public static void main(String[] args) {
        new GearKit(SkillLevel.BEGINNER.gearFactory()).print("Maya");
        new GearKit(SkillLevel.ADVANCED.gearFactory()).print("Jonas");
    }
}
