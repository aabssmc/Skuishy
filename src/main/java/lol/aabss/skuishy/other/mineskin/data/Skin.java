
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin.data;

import com.google.gson.annotations.SerializedName;

public class Skin {
    public int id;
    public String uuid;
    public String name;
    public SkinData data;
    public long timestamp;
    @SerializedName("private")
    public boolean prvate;
    public int views;
    public int accountId;
    public DelayInfo delayInfo;

    @Deprecated
    public double nextRequest;

}