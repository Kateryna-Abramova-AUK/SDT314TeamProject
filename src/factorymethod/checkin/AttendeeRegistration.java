package factorymethod.checkin;

abstract class AttendeeRegistration {
    private int registered = 0;

    /** Factory Method: subclasses decide which Attendee to create. */
    protected abstract Attendee createAttendee(String name, String detail);

    public final Attendee register(String name, String detail) {
        Attendee attendee = createAttendee(name, detail);
        registered++;
        System.out.println(getClass().getSimpleName() + " #" + registered
                + ": registered " + attendee.name());
        return attendee;
    }

    // Concrete creators, one per attendee type
    static class LessonRegistration extends AttendeeRegistration {
        @Override protected Attendee createAttendee(String name, String detail) {
            return new LessonBooking(name, detail);
        }
    }

    static class CompetitionRegistration extends AttendeeRegistration {
        @Override protected Attendee createAttendee(String name, String detail) {
            return new CompetitionEntry(name, detail);
        }
    }

    static class CampRegistration extends AttendeeRegistration {
        @Override protected Attendee createAttendee(String name, String detail) {
            return new CampEnrollment(name, detail);
        }
    }
}
