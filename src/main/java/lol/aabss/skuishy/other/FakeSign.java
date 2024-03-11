package lol.aabss.skuishy.other;

import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class FakeSign {

    public FakeSign(Sign newblock, BlockData oldblock, Player player) {
        this.newblock = newblock;
        this.oldblock = oldblock;
        this.player = player;
    }

    private final Sign newblock;
    private final BlockData oldblock;
    private final Player player;


    public Sign getNewBlock() {
        return newblock;
    }

    public BlockData getOldBlock() {
        return oldblock;
    }

    public Player getPlayer() {
        return player;
    }

    public void remove() {
        newblock.setType(oldblock.getMaterial());
        newblock.setBlockData(oldblock);
    }
}
