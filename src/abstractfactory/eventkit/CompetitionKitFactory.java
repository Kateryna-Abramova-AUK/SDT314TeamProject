package abstractfactory.eventkit;

import java.util.HashMap;
import java.util.Map;

final class CompetitionKitFactory implements EventKitFactory {
    private final Map<String, Double> scores = new HashMap<>();   // shared by scoring + board
    public RegistrationForm createRegistrationForm() { return new CompetitorRegistration(); }
    public ScoringSystem createScoringSystem()       { return new HeatScoring(scores); }
    public ResultsBoard createResultsBoard()         { return new LiveLeaderboard(scores); }

    // Concrete products of the competition family
    private static final class CompetitorRegistration implements RegistrationForm {
        public void register(String participant) {
            System.out.println("[Competition] " + participant + " registered: division chosen, waiver signed");
        }
    }

    private static final class HeatScoring implements ScoringSystem {
        private final Map<String, Double> scores;
        HeatScoring(Map<String, Double> scores) { this.scores = scores; }
        public void record(String participant, double value) {
            if (value < 0 || value > 10) {
                throw new IllegalArgumentException("Judge score must be between 0 and 10");
            }
            scores.merge(participant, value, Math::max);       // best ride counts
        }
    }

    private static final class LiveLeaderboard implements ResultsBoard {
        private final Map<String, Double> scores;
        LiveLeaderboard(Map<String, Double> scores) { this.scores = scores; }
        public void publish() {
            System.out.println("[Competition] Live leaderboard:");
            scores.entrySet().stream()
                  .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                  .forEach(e -> System.out.println("  " + e.getKey() + " - " + e.getValue() + " pts"));
        }
    }
}
