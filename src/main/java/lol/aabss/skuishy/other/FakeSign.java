package lol.aabss.skuishy.other;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class FakeSign {

    public FakeSign(Block originalblock, Sign newblock, Player player) {
        this.originalblock = originalblock;
        this.newblock = newblock;
        this.player = player;
    }

    private final Block originalblock;
    private final Sign newblock;
    private final Player player;

    public Block getOriginalBlock() {
        return originalblock;
    }

    public Sign getNewBlock() {
        return newblock;
    }

    public Player getPlayer() {
        return player;
    }

    public void remove() {
        newblock.setBlockData(originalblock.getBlockData());
    }
}
