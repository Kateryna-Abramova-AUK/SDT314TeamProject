package singleton.inventory;

class BookingModule {
    boolean isFree(String id) {
        return EquipmentInventory.getInstance().statusOf(id) == Status.AVAILABLE;
    }
}
