package builder.eventplan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final class CompetitionEvent {
    private final String name, location, permitNumber;
    private final LocalDate date;
    private final int heats, volunteersNeeded;
    private final List<String> judges, sponsors;
    private final double prizePool;
    private final boolean publicLeaderboard;

    private CompetitionEvent(Builder b) {
        this.name = b.name;
        this.date = b.date;
        this.location = b.location;
        this.permitNumber = b.permitNumber;
        this.heats = b.heats;
        this.judges = List.copyOf(b.judges);
        this.sponsors = List.copyOf(b.sponsors);
        this.volunteersNeeded = b.volunteersNeeded;
        this.prizePool = b.prizePool;
        this.publicLeaderboard = b.publicLeaderboard;
    }

    static Builder builder(String name, LocalDate date, String location) {
        return new Builder(name, date, location);
    }

    @Override public String toString() {
        return name + " on " + date + " at " + location + "\n"
                + "  permit: " + permitNumber + ", heats: " + heats + "\n"
                + "  judges: " + judges + "\n"
                + "  sponsors: " + sponsors + ", volunteers needed: " + volunteersNeeded + "\n"
                + "  prize pool: $" + String.format("%.2f", prizePool)
                + ", public leaderboard: " + publicLeaderboard;
    }

    static final class Builder {
        private final String name, location;
        private final LocalDate date;
        private String permitNumber;

        private int heats = 4;
        private final List<String> judges = new ArrayList<>();
        private final List<String> sponsors = new ArrayList<>();
        private int volunteersNeeded = 0;
        private double prizePool = 0;
        private boolean publicLeaderboard = true;

        private Builder(String name, LocalDate date, String location) {
            this.name = name;
            this.date = date;
            this.location = location;
        }

        Builder permit(String permitNumber)       { this.permitNumber = permitNumber; return this; }
        Builder heats(int heats)                  { this.heats = heats; return this; }
        Builder addJudge(String judge)            { this.judges.add(judge); return this; }
        Builder addSponsor(String sponsor)        { this.sponsors.add(sponsor); return this; }
        Builder volunteers(int count)             { this.volunteersNeeded = count; return this; }
        Builder prizePool(double amount)          { this.prizePool = amount; return this; }
        Builder publicLeaderboard(boolean on)     { this.publicLeaderboard = on; return this; }

        CompetitionEvent build() {
            if (permitNumber == null || permitNumber.isBlank()) {
                throw new IllegalStateException("A beach permit number is required");
            }
            if (heats <= 0) {
                throw new IllegalStateException("An event needs at least one heat");
            }
            if (judges.size() < 3) {
                throw new IllegalStateException("At least 3 judges are required");
            }
            if (prizePool < 0) {
                throw new IllegalStateException("Prize pool cannot be negative");
            }
            return new CompetitionEvent(this);
        }
    }
}
