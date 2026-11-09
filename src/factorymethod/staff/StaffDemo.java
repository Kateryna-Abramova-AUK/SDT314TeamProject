package factorymethod.staff;

import factorymethod.staff.StaffOnboarding.AssistantOnboarding;
import factorymethod.staff.StaffOnboarding.InstructorOnboarding;
import factorymethod.staff.StaffOnboarding.LifeguardOnboarding;
import java.util.List;

public class StaffDemo {
    public static void main(String[] args) {
        List<StaffMember> team = List.of(
                new InstructorOnboarding("ISA Level 2").onboard("Leo"),
                new LifeguardOnboarding("Ocean Rescue").onboard("Ira"),
                new AssistantOnboarding().onboard("Tim"));

        for (StaffMember member : team) {
            member.startShift();
        }
    }
}
