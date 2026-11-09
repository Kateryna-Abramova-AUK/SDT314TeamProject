package prototype.lessonplan;

import java.util.ArrayList;
import java.util.List;

final class LessonPlan implements Prototype<LessonPlan> {
    private String title;
    private final String skillLevel;
    private final int sessions;
    private String instructor = "unassigned";
    private final List<String> drills = new ArrayList<>();
    private final List<String> safetyChecks = new ArrayList<>();

    LessonPlan(String title, String skillLevel, int sessions) {
        this.title = title;
        this.skillLevel = skillLevel;
        this.sessions = sessions;
    }

    private LessonPlan(LessonPlan source) {
        this.title = source.title;
        this.skillLevel = source.skillLevel;
        this.sessions = source.sessions;
        this.instructor = source.instructor;
        this.drills.addAll(source.drills);
        this.safetyChecks.addAll(source.safetyChecks);
    }

    @Override public LessonPlan copy() { return new LessonPlan(this); }

    LessonPlan title(String title)           { this.title = title; return this; }
    LessonPlan instructor(String instructor) { this.instructor = instructor; return this; }
    LessonPlan addDrill(String drill)        { this.drills.add(drill); return this; }
    LessonPlan addSafetyCheck(String check)  { this.safetyChecks.add(check); return this; }

    @Override public String toString() {
        return title + " (" + skillLevel + ", " + sessions + " sessions, instructor: " + instructor + ")\n"
                + "  drills: " + drills + "\n"
                + "  safety checks: " + safetyChecks;
    }
}
