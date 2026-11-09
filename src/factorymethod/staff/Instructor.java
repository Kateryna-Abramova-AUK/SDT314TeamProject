package factorymethod.staff;

final class Instructor extends StaffMember {
    private final String certification;
    Instructor(String name, String staffId, String certification) {
        super(name, staffId);
        this.certification = certification;
    }
    @Override void startShift() { System.out.println(name + " is teaching a lesson"); }
    @Override String describe() { return staffId + " Instructor " + name + " (" + certification + ")"; }
}
