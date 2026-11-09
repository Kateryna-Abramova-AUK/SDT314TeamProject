package singleton.inventory;

import java.util.HashMap;
import java.util.Map;

final class EquipmentInventory {
    private static volatile EquipmentInventory instance;
    private final Map<String, Status> stock = new HashMap<>();

    private EquipmentInventory() { }

    static EquipmentInventory getInstance() {
        if (instance == null) {
            synchronized (EquipmentInventory.class) {
                if (instance == null) {
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
        return false;
    }

    synchronized void checkIn(String equipmentId, boolean conditionOk) {
        stock.put(equipmentId, conditionOk ? Status.AVAILABLE : Status.NEEDS_REPAIR);
    }

    synchronized Status statusOf(String equipmentId) {
        return stock.get(equipmentId);
    }
}
