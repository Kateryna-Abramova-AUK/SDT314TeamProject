package singleton.inventory;

public class InventoryDemo {
    public static void main(String[] args) {
        EquipmentInventory.getInstance().register("BOARD-01");
        RentalDesk desk = new RentalDesk();
        BookingModule booking = new BookingModule();

        System.out.println("Free before rental?  " + booking.isFree("BOARD-01"));
        System.out.println("Desk rents it:       " + desk.rent("BOARD-01"));
        System.out.println("Free after rental?   " + booking.isFree("BOARD-01"));
        desk.giveBack("BOARD-01", false);
        System.out.println("Rent damaged board:  " + desk.rent("BOARD-01"));
        System.out.println("Status: " + EquipmentInventory.getInstance().statusOf("BOARD-01"));
    }
}
