import java.util.HashMap;
import java.util.Map;

enum Status { AVAILABLE, RENTED, NEEDS_REPAIR }

final class EquipmentInventory {
    private static volatile EquipmentInventory instance;
    private final Map<String, Status> stock = new HashMap<>();

    private EquipmentInventory() { }

    static EquipmentInventory getInstance() {
        if (instance == null) {                              // first check, no lock
            synchronized (EquipmentInventory.class) {
                if (instance == null) {                      // second check, with lock
                    instance = new EquipmentInventory();
                }
            }
        }
        return instance;
    }

    synchronized void register(String equipmentId) {
        stock.put(equipmentId, Status.AVAILABLE);
    }

    synchronized boolean checkOut(String equipmentId) {
        if (stock.get(equipmentId) == Status.AVAILABLE) {
            stock.put(equipmentId, Status.RENTED);
            return true;
        }
        return false;                                         // damaged, rented, or unknown
    }

    synchronized void checkIn(String equipmentId, boolean conditionOk) {
        stock.put(equipmentId, conditionOk ? Status.AVAILABLE : Status.NEEDS_REPAIR);
    }

    synchronized Status statusOf(String equipmentId) {
        return stock.get(equipmentId);
    }
}

class RentalDesk {
    boolean rent(String id) { return EquipmentInventory.getInstance().checkOut(id); }
    void giveBack(String id, boolean ok) { EquipmentInventory.getInstance().checkIn(id, ok); }
}

class BookingModule {
    boolean isFree(String id) {
        return EquipmentInventory.getInstance().statusOf(id) == Status.AVAILABLE;
    }
}

public class InventoryDemo {
    public static void main(String[] args) {
        EquipmentInventory.getInstance().register("BOARD-01");
        RentalDesk desk = new RentalDesk();
        BookingModule booking = new BookingModule();

        System.out.println("Free before rental?  " + booking.isFree("BOARD-01"));   // true
        System.out.println("Desk rents it:       " + desk.rent("BOARD-01"));      // true
        System.out.println("Free after rental?   " + booking.isFree("BOARD-01"));   // false, same inventory
        desk.giveBack("BOARD-01", false);                                          // returned damaged
        System.out.println("Rent damaged board:  " + desk.rent("BOARD-01"));      // false
        System.out.println("Status: " + EquipmentInventory.getInstance().statusOf("BOARD-01"));
    }
}
