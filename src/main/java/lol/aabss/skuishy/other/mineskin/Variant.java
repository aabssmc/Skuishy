
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin;

public enum Variant {
    AUTO(""),
    CLASSIC("classic"),
    SLIM("slim");

    private final String name;

    Variant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}