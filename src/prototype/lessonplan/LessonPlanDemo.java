package prototype.lessonplan;

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
        System.out.println(august);
        System.out.println("Master unchanged: " + registry.create("beginner-camp"));
    }
}
