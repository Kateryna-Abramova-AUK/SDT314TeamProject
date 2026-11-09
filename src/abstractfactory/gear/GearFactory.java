package abstractfactory.gear;

/** Abstract Factory: creates one matching family of surf gear. */
interface GearFactory {
    SurfBoard createBoard();
    Wetsuit createWetsuit();
    Leash createLeash();

    // Abstract products
    interface SurfBoard { String describe(); }
    interface Wetsuit   { String describe(); }
    interface Leash     { String describe(); }
}
