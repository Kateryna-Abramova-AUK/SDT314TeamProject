package factorymethod.staff;

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

    // Concrete creators, one per role
    static class InstructorOnboarding extends StaffOnboarding {
        private final String certification;
        InstructorOnboarding(String certification) { this.certification = certification; }
        @Override protected String idPrefix() { return "INS"; }
        @Override protected StaffMember createStaffMember(String name, String staffId) {
            return new Instructor(name, staffId, certification);
        }
    }

    static class LifeguardOnboarding extends StaffOnboarding {
        private final String certification;
        LifeguardOnboarding(String certification) { this.certification = certification; }
        @Override protected String idPrefix() { return "LFG"; }
        @Override protected StaffMember createStaffMember(String name, String staffId) {
            return new Lifeguard(name, staffId, certification);
        }
    }

    static class AssistantOnboarding extends StaffOnboarding {
        @Override protected String idPrefix() { return "AST"; }
        @Override protected StaffMember createStaffMember(String name, String staffId) {
            return new AssistantInstructor(name, staffId);
        }
    }
}
