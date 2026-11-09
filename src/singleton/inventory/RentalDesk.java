package singleton.inventory;

class RentalDesk {
    boolean rent(String id) { return EquipmentInventory.getInstance().checkOut(id); }
    void giveBack(String id, boolean ok) { EquipmentInventory.getInstance().checkIn(id, ok); }
}
