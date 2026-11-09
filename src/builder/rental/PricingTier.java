package builder.rental;

enum PricingTier {
    HOURLY(1), DAILY(4), WEEKLY(20);
    final int multiplier;
    PricingTier(int multiplier) { this.multiplier = multiplier; }
}
