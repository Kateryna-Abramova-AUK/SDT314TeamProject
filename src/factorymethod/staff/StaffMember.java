package factorymethod.staff;

abstract class StaffMember {
    protected final String name;
    protected final String staffId;

    StaffMember(String name, String staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    void clockIn() { System.out.println(name + " clocked in"); }
    abstract String describe();
    abstract void startShift();
}
