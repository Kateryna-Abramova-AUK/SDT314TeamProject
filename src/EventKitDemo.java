import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

interface RegistrationForm { void register(String participant); }
interface ScoringSystem    { void record(String participant, double value); }
interface ResultsBoard     { void publish(); }

interface EventKitFactory {
    RegistrationForm createRegistrationForm();
    ScoringSystem createScoringSystem();
    ResultsBoard createResultsBoard();
}

final class CompetitorRegistration implements RegistrationForm {
    public void register(String participant) {
        System.out.println("[Competition] " + participant + " registered: division chosen, waiver signed");
    }
}

final class HeatScoring implements ScoringSystem {
    private final Map<String, Double> scores;
    HeatScoring(Map<String, Double> scores) { this.scores = scores; }
    public void record(String participant, double value) {
        if (value < 0 || value > 10) {
            throw new IllegalArgumentException("Judge score must be between 0 and 10");
        }
        scores.merge(participant, value, Math::max);       // best ride counts
    }
}

final class LiveLeaderboard implements ResultsBoard {
    private final Map<String, Double> scores;
    LiveLeaderboard(Map<String, Double> scores) { this.scores = scores; }
    public void publish() {
        System.out.println("[Competition] Live leaderboard:");
        scores.entrySet().stream()
              .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
              .forEach(e -> System.out.println("  " + e.getKey() + " - " + e.getValue() + " pts"));
    }
}

final class CompetitionKitFactory implements EventKitFactory {
    private final Map<String, Double> scores = new HashMap<>();   // shared by scoring + board
    public RegistrationForm createRegistrationForm() { return new CompetitorRegistration(); }
    public ScoringSystem createScoringSystem()       { return new HeatScoring(scores); }
    public ResultsBoard createResultsBoard()         { return new LiveLeaderboard(scores); }
}

final class SignUpSheet implements RegistrationForm {
    public void register(String participant) {
        System.out.println("[Community] " + participant + " signed up: name only, no waiver needed");
    }
}

final class ParticipationLog implements ScoringSystem {
    private final Map<String, Double> hours;
    ParticipationLog(Map<String, Double> hours) { this.hours = hours; }
    public void record(String participant, double value) {
        hours.merge(participant, value, Double::sum);        // hours add up
    }
}

final class ThankYouBoard implements ResultsBoard {
    private final Map<String, Double> hours;
    ThankYouBoard(Map<String, Double> hours) { this.hours = hours; }
    public void publish() {
        System.out.println("[Community] Thank-you board (no ranking):");
        hours.forEach((name, h) -> System.out.println("  " + name + " - " + h + " volunteer hours"));
    }
}

final class CommunityKitFactory implements EventKitFactory {
    private final Map<String, Double> hours = new TreeMap<>();    // alphabetical, not ranked
    public RegistrationForm createRegistrationForm() { return new SignUpSheet(); }
    public ScoringSystem createScoringSystem()       { return new ParticipationLog(hours); }
    public ResultsBoard createResultsBoard()         { return new ThankYouBoard(hours); }
}

final class EventDesk {
    static void run(String title, EventKitFactory kit, String[] people, double[] values) {
        RegistrationForm form = kit.createRegistrationForm();
        ScoringSystem scoring = kit.createScoringSystem();
        ResultsBoard board = kit.createResultsBoard();

        System.out.println("=== " + title + " ===");
        for (int i = 0; i < people.length; i++) {
            form.register(people[i]);
            scoring.record(people[i], values[i]);
        }
        board.publish();
    }
}

public class EventKitDemo {
    public static void main(String[] args) {
        EventDesk.run("Summer Open", new CompetitionKitFactory(),
                new String[] {"Jonas", "Maya", "Leo"}, new double[] {8.5, 9.1, 7.2});
        EventDesk.run("Beach Clean-Up", new CommunityKitFactory(),
                new String[] {"Ben", "Anna"}, new double[] {2.5, 3.0});
    }
}
