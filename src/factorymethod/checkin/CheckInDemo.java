package factorymethod.checkin;

import factorymethod.checkin.AttendeeRegistration.CampRegistration;
import factorymethod.checkin.AttendeeRegistration.CompetitionRegistration;
import factorymethod.checkin.AttendeeRegistration.LessonRegistration;
import java.util.ArrayList;
import java.util.List;

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
