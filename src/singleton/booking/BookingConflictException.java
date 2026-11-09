package singleton.booking;

final class BookingConflictException extends RuntimeException {
    BookingConflictException(String message) { super(message); }
}
