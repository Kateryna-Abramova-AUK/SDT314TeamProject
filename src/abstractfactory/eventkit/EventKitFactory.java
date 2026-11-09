package abstractfactory.eventkit;

/** Abstract Factory: creates one matching family of event tools. */
interface EventKitFactory {
    RegistrationForm createRegistrationForm();
    ScoringSystem createScoringSystem();
    ResultsBoard createResultsBoard();

    // Abstract products
    interface RegistrationForm { void register(String participant); }
    interface ScoringSystem    { void record(String participant, double value); }
    interface ResultsBoard     { void publish(); }
}
