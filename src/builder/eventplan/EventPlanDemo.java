package builder.eventplan;

import java.time.LocalDate;

public class EventPlanDemo {
    public static void main(String[] args) {
        CompetitionEvent summerOpen = CompetitionEvent
                .builder("Summer Open", LocalDate.of(2027, 7, 17), "North Beach")
                .permit("P-2027-114")
                .heats(8)
                .addJudge("Dana").addJudge("Omar").addJudge("Eli")
                .addSponsor("Seaside Hotel").addSponsor("Wave Wax Co.")
                .volunteers(20)
                .prizePool(3000)
                .build();
        System.out.println(summerOpen);

        try {
            CompetitionEvent.builder("Night Session", LocalDate.of(2027, 8, 1), "North Beach")
                    .addJudge("Dana")
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
