package factorymethod.staff;

final class Lifeguard extends StaffMember {
    private final String certification;
    Lifeguard(String name, String staffId, String certification) {
        super(name, staffId);
        this.certification = certification;
    }
    @Override void startShift() { System.out.println(name + " is watching the lineup"); }
    @Override String describe() { return staffId + " Lifeguard " + name + " (" + certification + ")"; }
}
