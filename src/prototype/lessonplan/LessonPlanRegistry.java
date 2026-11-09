package prototype.lessonplan;

import java.util.HashMap;
import java.util.Map;

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
