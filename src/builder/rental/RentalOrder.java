package builder.rental;

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
