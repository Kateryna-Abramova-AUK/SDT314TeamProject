package factorymethod.checkin;

final class CampEnrollment implements Attendee {
    private final String name;
    private final String camp;
    CampEnrollment(String name, String camp) { this.name = name; this.camp = camp; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": camper arrival logged (" + camp + ")");
    }
}
