package abstractfactory.gear;

import abstractfactory.gear.GearFactory.Leash;
import abstractfactory.gear.GearFactory.SurfBoard;
import abstractfactory.gear.GearFactory.Wetsuit;

final class GearKit {
    private final SurfBoard board;
    private final Wetsuit wetsuit;
    private final Leash leash;

    GearKit(GearFactory factory) {
        this.board = factory.createBoard();
        this.wetsuit = factory.createWetsuit();
        this.leash = factory.createLeash();
    }

    void print(String customer) {
        System.out.println(customer + "'s kit:");
        System.out.println("  - " + board.describe());
        System.out.println("  - " + wetsuit.describe());
        System.out.println("  - " + leash.describe());
    }
}
