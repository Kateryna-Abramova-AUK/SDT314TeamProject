import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class MessageTemplate implements Cloneable {
    private final String subject;
    private final String greeting;
    private final String signOff;
    private List<String> lines = new ArrayList<>();          // not final: clone() replaces it

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

public class MessageTemplateDemo {
    private static final String GREETING = "Hi {name}, thanks for riding with Tidebreaker!";
    private static final String SIGN_OFF = "See you in the water, the Tidebreaker crew";

    public static void main(String[] args) {
        MessageLibrary library = new MessageLibrary();
        library.approve("booking-confirmed",
                new MessageTemplate("You're booked for {service}", GREETING, SIGN_OFF)
                        .addLine("Your spot for {service} on {slot} is confirmed.")
                        .addLine("Bring water and sunscreen, we'll bring the boards."));
        library.approve("weather-change",
                new MessageTemplate("Change of plans for {service}", GREETING, SIGN_OFF)
                        .addLine("The ocean isn't safe for {service} on {slot}.")
                        .addLine("Reply to this message and we'll find you a new time."));

        Map<String, String> anna = Map.of("name", "Anna", "service", "Beginner lesson", "slot", "Sat 09:00");
        System.out.println(library.create("booking-confirmed").render(anna));

        // the phone desk adds a note for one customer; the approved wording stays as it was
        MessageTemplate forBen = library.create("weather-change")
                .addLine("Your deposit carries over to the new date.");
        Map<String, String> ben = Map.of("name", "Ben", "service", "Kids Camp", "slot", "Mon 10:00");
        System.out.println(forBen.render(ben));
        System.out.println(library.create("weather-change").render(ben));
    }
}
