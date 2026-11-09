package builder.rental;

final class RentalOrderBuilder {
    private static final double BOARD = 25, WETSUIT = 15, LEASH = 5,
            FINS = 8, ROOF_RACK = 10, INSURANCE = 12;

    final String customer;
    final PricingTier tier;
    String board, wetsuit, leash, fins;
    boolean roofRack, insurance;

    RentalOrderBuilder(String customer, PricingTier tier) {
        this.customer = customer;
        this.tier = tier;
    }

    RentalOrderBuilder addBoard(String board)     { this.board = board; return this; }
    RentalOrderBuilder addWetsuit(String wetsuit) { this.wetsuit = wetsuit; return this; }
    RentalOrderBuilder addLeash(String leash)     { this.leash = leash; return this; }
    RentalOrderBuilder addFins(String fins)       { this.fins = fins; return this; }
    RentalOrderBuilder addRoofRack()              { this.roofRack = true; return this; }
    RentalOrderBuilder addInsurance()             { this.insurance = true; return this; }

    RentalOrder build() {
        if (board == null && wetsuit == null) {
            throw new IllegalStateException("Order needs at least a board or a wetsuit");
        }
        if (roofRack && board == null) {
            throw new IllegalStateException("Roof rack needs a board to carry");
        }
        double base = 0;
        if (board != null)   base += BOARD;
        if (wetsuit != null) base += WETSUIT;
        if (leash != null)   base += LEASH;
        if (fins != null)    base += FINS;
        if (roofRack)        base += ROOF_RACK;
        if (insurance)       base += INSURANCE;
        return new RentalOrder(this, base * tier.multiplier);
    }
}
