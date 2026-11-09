package factorymethod.checkin;

final class CompetitionEntry implements Attendee {
    private final String name;
    private final String heat;
    CompetitionEntry(String name, String heat) { this.name = name; this.heat = heat; }
    public String name() { return name; }
    public void checkIn() {
        System.out.println(name + ": heat start time verified (" + heat + ")");
    }
}
