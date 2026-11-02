import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Prototype<T> {
    T copy();
}

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

    /** Copy constructor: new lists, so the clone is fully independent. */
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

/** Stores approved master templates and hands out copies, never the masters. */
final class LessonPlanRegistry {
    private final Map<String, LessonPlan> templates = new HashMap<>();

    void register(String key, LessonPlan template) {
        templates.put(key, template.copy());
    }

    LessonPlan create(String key) {
        LessonPlan template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("No lesson template named " + key);
        }
        return template.copy();
    }
}

public class LessonPlanDemo {
    public static void main(String[] args) {
        LessonPlanRegistry registry = new LessonPlanRegistry();
        registry.register("beginner-camp",
                new LessonPlan("Beginner Camp", "BEGINNER", 5)
                        .addDrill("Beach pop-up practice")
                        .addDrill("Whitewater paddling")
                        .addSafetyCheck("Rip current briefing")
                        .addSafetyCheck("Leash and board inspection"));

        LessonPlan july = registry.create("beginner-camp")
                .title("Beginner Camp - July")
                .instructor("Leo");
        LessonPlan august = registry.create("beginner-camp")
                .title("Beginner Camp - August (Kids)")
                .instructor("Maya")
                .addDrill("Video review")
                .addSafetyCheck("Parent contact sheet");

        System.out.println(july);
        System.out.println(august);                                // extras only on August
        System.out.println("Master unchanged: " + registry.create("beginner-camp"));
    }
}
