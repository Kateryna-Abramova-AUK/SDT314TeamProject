package prototype.messagetemplate;

import java.util.HashMap;
import java.util.Map;

final class MessageLibrary {
    private final Map<String, MessageTemplate> approved = new HashMap<>();

    void approve(String key, MessageTemplate template) {
        approved.put(key, template.clone());
    }

    MessageTemplate create(String key) {
        MessageTemplate template = approved.get(key);
        if (template == null) {
            throw new IllegalArgumentException("No approved template named " + key);
        }
        return template.clone();
    }
}
