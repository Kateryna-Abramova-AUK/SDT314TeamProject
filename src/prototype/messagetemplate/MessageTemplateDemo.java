package prototype.messagetemplate;

import java.util.Map;

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

        MessageTemplate forBen = library.create("weather-change")
                .addLine("Your deposit carries over to the new date.");
        Map<String, String> ben = Map.of("name", "Ben", "service", "Kids Camp", "slot", "Mon 10:00");
        System.out.println(forBen.render(ben));
        System.out.println(library.create("weather-change").render(ben));
    }
}
