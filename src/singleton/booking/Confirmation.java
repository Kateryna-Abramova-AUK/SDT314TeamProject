package singleton.booking;

final class Confirmation {
    private final String text;
    Confirmation(String text) { this.text = text; }
    @Override public String toString() { return "Confirmation[" + text + "]"; }
}
