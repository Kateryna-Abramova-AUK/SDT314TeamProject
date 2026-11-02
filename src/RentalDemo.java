enum PricingTier {
    HOURLY(1), DAILY(4), WEEKLY(20);
    final int multiplier;
    PricingTier(int multiplier) { this.multiplier = multiplier; }
}

final class RentalOrder {
    private final String customer;
    private final PricingTier tier;
    private final String board, wetsuit, leash, fins;
    private final boolean roofRack, insurance;
    private final double total;

    RentalOrder(RentalOrderBuilder b, double total) {
        this.customer = b.customer;
        this.tier = b.tier;
        this.board = b.board;
        this.wetsuit = b.wetsuit;
        this.leash = b.leash;
        this.fins = b.fins;
        this.roofRack = b.roofRack;
        this.insurance = b.insurance;
        this.total = total;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("Rental for " + customer + " (" + tier + ")\n");
        if (board != null)   sb.append("  board:   ").append(board).append('\n');
        if (wetsuit != null) sb.append("  wetsuit: ").append(wetsuit).append('\n');
        if (leash != null)   sb.append("  leash:   ").append(leash).append('\n');
        if (fins != null)    sb.append("  fins:    ").append(fins).append('\n');
        if (roofRack)        sb.append("  roof rack\n");
        if (insurance)       sb.append("  insurance add-on\n");
        return sb.append("  total: $").append(String.format("%.2f", total)).toString();
    }
}

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

final class RentalPackages {
    static RentalOrder beginnerPackage(String customer, PricingTier tier) {
        return new RentalOrderBuilder(customer, tier)
                .addBoard("9 ft foam longboard")
                .addWetsuit("4/3 mm padded wetsuit")
                .addLeash("Coiled leash")
                .addInsurance()
                .build();
    }
}

public class RentalDemo {
    public static void main(String[] args) {
        RentalOrder custom = new RentalOrderBuilder("Jonas", PricingTier.DAILY)
                .addBoard("6 ft shortboard")
                .addFins("Thruster set")
                .addRoofRack()
                .build();
        System.out.println(custom);

        System.out.println(RentalPackages.beginnerPackage("Maya", PricingTier.WEEKLY));

        try {
            new RentalOrderBuilder("Leo", PricingTier.HOURLY).addInsurance().build();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
