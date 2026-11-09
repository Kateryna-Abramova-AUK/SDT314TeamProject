package abstractfactory.eventkit;

public class EventKitDemo {
    public static void main(String[] args) {
        EventDesk.run("Summer Open", new CompetitionKitFactory(),
                new String[] {"Jonas", "Maya", "Leo"}, new double[] {8.5, 9.1, 7.2});
        EventDesk.run("Beach Clean-Up", new CommunityKitFactory(),
                new String[] {"Ben", "Anna"}, new double[] {2.5, 3.0});
    }
}
