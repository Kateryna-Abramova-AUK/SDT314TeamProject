package singleton.booking;

import java.util.HashMap;
import java.util.Map;

final class BookingSystem {
    private final Map<String, Integer> remaining = new HashMap<>();
    private int nextId = 1;

    private BookingSystem() { }

    private static final class Holder {
        private static final BookingSystem INSTANCE = new BookingSystem();
    }

    static BookingSystem getInstance() { return Holder.INSTANCE; }

    synchronized void openSlot(String serviceType, String timeSlot, int capacity) {
        remaining.put(key(serviceType, timeSlot), capacity);
    }

    synchronized Confirmation book(String customer, String serviceType, String timeSlot) {
        String key = key(serviceType, timeSlot);
        int left = remaining.getOrDefault(key, 0);
        if (left <= 0) {
            throw new BookingConflictException("No availability for " + key);
        }
        remaining.put(key, left - 1);
        return new Confirmation("TB-" + nextId++ + ": " + customer + ", " + key);
    }

    synchronized int available(String serviceType, String timeSlot) {
        return remaining.getOrDefault(key(serviceType, timeSlot), 0);
    }

    private static String key(String serviceType, String timeSlot) {
        return serviceType + " @ " + timeSlot;
    }
}
