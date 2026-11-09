package builder.rental;

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
