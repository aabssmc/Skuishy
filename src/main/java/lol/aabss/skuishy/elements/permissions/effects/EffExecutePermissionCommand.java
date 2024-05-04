package lol.aabss.skuishy.elements.permissions.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

import static lol.aabss.skuishy.Skuishy.instance;
import static org.bukkit.Bukkit.getServer;

@Name("Permissions - Execute Permission Command")
@Description("Makes a player executes a command with a certain permission.")
@Examples({
        "# will be safe !!!",
        "make all players execute command \"minecraft:teammsg %arg-1%\" using permission named \"*\""
})
@Since("2.7")
public class EffExecutePermissionCommand extends Effect implements Listener {

    private static final HashMap<Permissible, String> map = new HashMap<>();

    static {
        getServer().getPluginManager().registerEvents(new EffExecutePermissionCommand(), instance);
        Skript.registerEffect(EffExecutePermissionCommand.class,
                "[execute] [the] %players% command[s] %string% (with|[while] using) %bukkitpermission%",
                "(let|make) %players% execute [[the] command[s]] %string% (with|[while] using) %bukkitpermission%"
        );
    }

    private Expression<Player> player;
    private Expression<String> command;
    private Expression<Permission> permission;

    @Override
    protected void execute(@NotNull Event event) {
        if (player != null && permission != null) {
            Permission permission = this.permission.getSingle(event);
            String command = this.command.getSingle(event);
            if (player == null || command == null){
                return;
            }
            for (Player p : player.getArray(event)) {
                map.put(p, command);
                PermissionAttachment attachment = new PermissionAttachment(instance, p);
                attachment.setPermission(permission, true);
                p.performCommand(command);
                attachment.remove();
                map.remove(p, command);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "make player execute command with permission";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        command = (Expression<String>) expressions[1];
        permission = (Expression<Permission>) expressions[2];
        return true;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        String m = event.getMessage();
        if (map.containsKey(p)){
            if (!Objects.equals(m, map.get(p)) ||
                    !Objects.equals("/"+m, map.get(p))){
                event.setCancelled(true);
            }
        }
    }
}
