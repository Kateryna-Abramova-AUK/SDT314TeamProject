package factorymethod.staff;

final class AssistantInstructor extends StaffMember {
    AssistantInstructor(String name, String staffId) { super(name, staffId); }
    @Override void startShift() { System.out.println(name + " is assisting the instructor"); }
    @Override String describe() { return staffId + " Assistant Instructor " + name; }
}
