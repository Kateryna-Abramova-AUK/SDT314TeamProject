package abstractfactory.eventkit;

import java.util.Map;
import java.util.TreeMap;

final class CommunityKitFactory implements EventKitFactory {
    private final Map<String, Double> hours = new TreeMap<>();
    public RegistrationForm createRegistrationForm() { return new SignUpSheet(); }
    public ScoringSystem createScoringSystem()       { return new ParticipationLog(hours); }
    public ResultsBoard createResultsBoard()         { return new ThankYouBoard(hours); }

    // Concrete products of the community family
    private static final class SignUpSheet implements RegistrationForm {
        public void register(String participant) {
            System.out.println("[Community] " + participant + " signed up: name only, no waiver needed");
        }
    }

    private static final class ParticipationLog implements ScoringSystem {
        private final Map<String, Double> hours;
        ParticipationLog(Map<String, Double> hours) { this.hours = hours; }
        public void record(String participant, double value) {
            hours.merge(participant, value, Double::sum);        // hours add up
        }
    }

    private static final class ThankYouBoard implements ResultsBoard {
        private final Map<String, Double> hours;
        ThankYouBoard(Map<String, Double> hours) { this.hours = hours; }
        public void publish() {
            System.out.println("[Community] Thank-you board (no ranking):");
            hours.forEach((name, h) -> System.out.println("  " + name + " - " + h + " volunteer hours"));
        }
    }
}
