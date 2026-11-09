package abstractfactory.gear;

enum SkillLevel {
    BEGINNER, ADVANCED;

    GearFactory gearFactory() {
        return switch (this) {
            case BEGINNER -> new BeginnerGearFactory();
            case ADVANCED -> new AdvancedGearFactory();
        };
    }
}
