import java.util.ArrayList;
import java.util.List;

interface Attendee {
    String name();
    void checkIn();
}

final class LessonBooking implements Attendee {
    private final String name;
    private final String instructor;
    LessonBooking(String name, String instructor) { this.name = name; this.instructor = instructor; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": lesson attendance marked (instructor " + instructor + ")");
    }
}

final class CompetitionEntry implements Attendee {
    private final String name;
    private final String heat;
    CompetitionEntry(String name, String heat) { this.name = name; this.heat = heat; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": heat start time verified (" + heat + ")");
    }
}

final class CampEnrollment implements Attendee {
    private final String name;
    private final String camp;
    CampEnrollment(String name, String camp) { this.name = name; this.camp = camp; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": camper arrival logged (" + camp + ")");
    }
}

abstract class AttendeeRegistration {
    private int registered = 0;

    protected abstract Attendee createAttendee(String name, String detail);

    public final Attendee register(String name, String detail) {
        Attendee attendee = createAttendee(name, detail);
        registered++;
        System.out.println(getClass().getSimpleName() + " #" + registered
                + ": registered " + attendee.name());
        return attendee;
    }
}

class LessonRegistration extends AttendeeRegistration {
    @Override protected Attendee createAttendee(String name, String detail) {
        return new LessonBooking(name, detail);
    }
}

class CompetitionRegistration extends AttendeeRegistration {
    @Override protected Attendee createAttendee(String name, String detail) {
        return new CompetitionEntry(name, detail);
    }
}

class CampRegistration extends AttendeeRegistration {
    @Override protected Attendee createAttendee(String name, String detail) {
        return new CampEnrollment(name, detail);
    }
}

public class CheckInDemo {
    public static void main(String[] args) {
        AttendeeRegistration lessons = new LessonRegistration();
        AttendeeRegistration competitions = new CompetitionRegistration();
        AttendeeRegistration camps = new CampRegistration();

        List<Attendee> todaysAttendees = new ArrayList<>();
        todaysAttendees.add(lessons.register("Maya", "Coach Leo"));
        todaysAttendees.add(competitions.register("Jonas", "Heat 3 at 10:40"));
        todaysAttendees.add(camps.register("Sofia", "Kids Camp, week 2"));

        for (Attendee attendee : todaysAttendees) {
            attendee.checkIn();
        }
    }
}
