// ---------- Product hierarchy (from the Inheritance module) ----------
abstract class StaffMember {
    protected final String name;
    protected final String staffId;

    StaffMember(String name, String staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    void clockIn() { System.out.println(name + " clocked in"); }
    abstract String describe();
}

final class Instructor extends StaffMember {
    private final String certification;
    Instructor(String name, String staffId, String certification) {
        super(name, staffId);
        this.certification = certification;
    }
    void teachLesson(String student) { System.out.println(name + " is teaching " + student); }
    @Override String describe() { return staffId + " Instructor " + name + " (" + certification + ")"; }
}

final class Lifeguard extends StaffMember {
    private final String certification;
    Lifeguard(String name, String staffId, String certification) {
        super(name, staffId);
        this.certification = certification;
    }
    void monitorWater() { System.out.println(name + " is watching the lineup"); }
    @Override String describe() { return staffId + " Lifeguard " + name + " (" + certification + ")"; }
}

final class AssistantInstructor extends StaffMember {
    AssistantInstructor(String name, String staffId) { super(name, staffId); }
    void assist(String instructor) { System.out.println(name + " is assisting " + instructor); }
    @Override String describe() { return staffId + " Assistant Instructor " + name; }
}

// ---------- Creator ----------
abstract class StaffOnboarding {
    private int sequence = 0;

    protected abstract String idPrefix();

    /** Factory Method: subclasses decide which StaffMember to create. */
    protected abstract StaffMember createStaffMember(String name, String staffId);

    /** Fixed onboarding workflow shared by every role. */
    final StaffMember onboard(String name) {
        String staffId = idPrefix() + "-" + String.format("%03d", ++sequence);
        StaffMember member = createStaffMember(name, staffId);
        System.out.println("Onboarded " + member.describe());
        member.clockIn();
        return member;
    }
}

// ---------- Concrete creators ----------
class InstructorOnboarding extends StaffOnboarding {
    private final String certification;
    InstructorOnboarding(String certification) { this.certification = certification; }
    @Override protected String idPrefix() { return "INS"; }
    @Override protected StaffMember createStaffMember(String name, String staffId) {
        return new Instructor(name, staffId, certification);
    }
}

class LifeguardOnboarding extends StaffOnboarding {
    private final String certification;
    LifeguardOnboarding(String certification) { this.certification = certification; }
    @Override protected String idPrefix() { return "LFG"; }
    @Override protected StaffMember createStaffMember(String name, String staffId) {
        return new Lifeguard(name, staffId, certification);
    }
}

class AssistantOnboarding extends StaffOnboarding {
    @Override protected String idPrefix() { return "AST"; }
    @Override protected StaffMember createStaffMember(String name, String staffId) {
        return new AssistantInstructor(name, staffId);
    }
}

public class StaffDemo {
    public static void main(String[] args) {
        StaffMember leo = new InstructorOnboarding("ISA Level 2").onboard("Leo");
        StaffMember ira = new LifeguardOnboarding("Ocean Rescue").onboard("Ira");
        StaffMember tim = new AssistantOnboarding().onboard("Tim");

        ((Instructor) leo).teachLesson("Maya");
        ((Lifeguard) ira).monitorWater();
        ((AssistantInstructor) tim).assist("Leo");
    }
}
