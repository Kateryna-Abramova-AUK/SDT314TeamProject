package abstractfactory.eventkit;

import abstractfactory.eventkit.EventKitFactory.RegistrationForm;
import abstractfactory.eventkit.EventKitFactory.ResultsBoard;
import abstractfactory.eventkit.EventKitFactory.ScoringSystem;

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
