package lol.aabss.skuishy.elements.decentholograms.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Section;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;

import org.jetbrains.annotations.Nullable;
import java.util.List;

@Name("DecentHolograms - Clone Hologram")
@Examples({
        "clone hologram named \"lol\":",
        "\tname: \"lol2\"",
        "\tlocation: player's location"})
@Description("Clones a hologram")
@Since("2.0")
@RequiredPlugins("DecentHolograms")

public class SecCloneHologram extends Section {

    private static final EntryValidator.EntryValidatorBuilder ENTRY_VALIDATOR = EntryValidator.builder();

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerSection(SecCloneHologram.class,
                    "[temp:temp[orar[il]y]] clone [(decent [hologram[s]]|dh)] %hologram%"
            );
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("name", null, false, String.class));
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("location", null, false, Location.class));
        }
    }

    private Expression<Hologram> hologram;
    private Expression<String> name;
    private Expression<Location> location;
    private boolean temp;

    @Override
    public boolean init(@NotNull Expression<?> @NotNull [] exprs,
                        int matchedPattern,
                        @NotNull Kleenean isDelayed,
                        SkriptParser.@NotNull ParseResult parseResult,
                        @NotNull SectionNode sectionNode,
                        @NotNull List<TriggerItem> triggerItems) {
        EntryContainer container = ENTRY_VALIDATOR.build().validate(sectionNode);
        if (container == null) return false;
        hologram = (Expression<Hologram>) exprs[0];
        temp = parseResult.hasTag("temp");
        name = (Expression<String>) container.getOptional("name", false);
        location = (Expression<Location>) container.getOptional("location", false);
        return hologram != null && name != null && location != null;
    }

    @Override
    protected @Nullable TriggerItem walk(@NotNull Event event) {
        if (hologram != null && name != null && location != null) {
            Hologram holo = this.hologram.getSingle(event);
            String name = this.name.getSingle(event);
            Location loc = this.location.getSingle(event);
            if (holo != null && name != null && loc != null) {
                holo.clone(name, loc, temp);
            }
        }
        return super.walk(event, false);
    }

    @NotNull
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "clone a hologram";
    }
}
