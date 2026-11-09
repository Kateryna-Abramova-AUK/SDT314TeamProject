package prototype.messagetemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class MessageTemplate implements Cloneable {
    private final String subject;
    private final String greeting;
    private final String signOff;
    private List<String> lines = new ArrayList<>();

    MessageTemplate(String subject, String greeting, String signOff) {
        this.subject = subject;
        this.greeting = greeting;
        this.signOff = signOff;
    }

    MessageTemplate addLine(String line) { this.lines.add(line); return this; }

    @Override public MessageTemplate clone() {
        try {
            MessageTemplate copy = (MessageTemplate) super.clone();
            copy.lines = new ArrayList<>(this.lines);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    String render(Map<String, String> values) {
        StringBuilder sb = new StringBuilder("Subject: " + subject + "\n" + greeting + "\n");
        for (String line : lines) {
            sb.append("  ").append(line).append('\n');
        }
        String text = sb.append(signOff).toString();
        for (Map.Entry<String, String> value : values.entrySet()) {
            text = text.replace("{" + value.getKey() + "}", value.getValue());
        }
        return text;
    }
}
