
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin;

public enum Visibility {

    PUBLIC(0),
    PRIVATE(1);

    private final int code;

    Visibility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
