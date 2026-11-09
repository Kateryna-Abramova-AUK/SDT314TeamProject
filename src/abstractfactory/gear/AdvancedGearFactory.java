package abstractfactory.gear;

final class AdvancedGearFactory implements GearFactory {
    public SurfBoard createBoard()   { return new PerformanceShortboard(); }
    public Wetsuit createWetsuit()   { return new FlexWetsuit(); }
    public Leash createLeash()       { return new StraightLeash(); }

    // Concrete products of the advanced family
    private static final class PerformanceShortboard implements SurfBoard {
        public String describe() { return "6 ft performance shortboard (fast, responsive)"; }
    }
    private static final class FlexWetsuit implements Wetsuit {
        public String describe() { return "3/2 mm stretch full suit"; }
    }
    private static final class StraightLeash implements Leash {
        public String describe() { return "6 ft straight performance leash"; }
    }
}
