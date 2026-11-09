package singleton.booking;

public class BookingSystemDemo {
    public static void main(String[] args) {
        BookingSystem frontDesk = BookingSystem.getInstance();
        BookingSystem customerApp = BookingSystem.getInstance();
        BookingSystem phoneDesk = BookingSystem.getInstance();
        System.out.println("Same instance? " + (frontDesk == customerApp && customerApp == phoneDesk));

        frontDesk.openSlot("Beginner lesson", "Sat 09:00", 1);
        System.out.println(customerApp.book("Anna", "Beginner lesson", "Sat 09:00"));
        try {
            phoneDesk.book("Ben", "Beginner lesson", "Sat 09:00");
        } catch (BookingConflictException e) {
            System.out.println("Phone desk: " + e.getMessage());
        }
    }
}
