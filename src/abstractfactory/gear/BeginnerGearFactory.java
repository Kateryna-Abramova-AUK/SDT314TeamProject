package abstractfactory.gear;

final class BeginnerGearFactory implements GearFactory {
    public SurfBoard createBoard()   { return new FoamLongboard(); }
    public Wetsuit createWetsuit()   { return new PaddedWetsuit(); }
    public Leash createLeash()       { return new CoiledLeash(); }

    // Concrete products of the beginner family
    private static final class FoamLongboard implements SurfBoard {
        public String describe() { return "9 ft foam soft-top longboard (stable, forgiving)"; }
    }
    private static final class PaddedWetsuit implements Wetsuit {
        public String describe() { return "4/3 mm full suit with extra padding"; }
    }
    private static final class CoiledLeash implements Leash {
        public String describe() { return "9 ft coiled ankle leash (stays out of the way)"; }
    }
}
