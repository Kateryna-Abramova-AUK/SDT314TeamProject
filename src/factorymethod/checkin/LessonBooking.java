package factorymethod.checkin;

final class LessonBooking implements Attendee {
    private final String name;
    private final String instructor;
    LessonBooking(String name, String instructor) { this.name = name; this.instructor = instructor; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": lesson attendance marked (instructor " + instructor + ")");
    }
}
