package builder.rental;

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
