package lol.aabss.skuishy.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Section;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Name("DecentHolograms - Create Hologram")
@Examples({
        "make a new dh hologram:",
        "\tname: \"big juice\"",
        "\tlocation: player's location",
        "\tlines: \"&fi love the juicy juices\", \"&7(they are really juicy)\""})
@Since("1.7")

public class SecCreateHologram extends Section {

    static final EntryValidator.EntryValidatorBuilder ENTRY_VALIDATOR = EntryValidator.builder();

    static {
        Skript.registerSection(SecCreateHologram.class,
                "(create|make) [a] [new] [:persistent] [(decent [hologram[s]]|dh)] hologram"
        );
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("name", null, false, String.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("location", null, false, Location.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("lines", null, false, String.class));
    }

    private Expression<String> name;
    private Expression<Location> location;
    private Expression<String> lines;
    private boolean persistent;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(@NotNull Expression<?> @NotNull [] exprs,
                        int matchedPattern,
                        @NotNull Kleenean isDelayed,
                        @NotNull SkriptParser.ParseResult parseResult,
                        @NotNull SectionNode sectionNode,
                        @NotNull List<TriggerItem> triggerItems) {
        EntryContainer container = ENTRY_VALIDATOR.build().validate(sectionNode);
        if (container == null) return false;
        persistent = parseResult.hasTag("persistent");
        name = (Expression<String>) container.getOptional("name", false);
        if (name == null) return false;
        location = (Expression<Location>) container.getOptional("location", false);
        if (location == null) return false;
        lines = (Expression<String>) container.getOptional("lines", false);
        return lines != null;
    }

    @Override
    protected @Nullable TriggerItem walk(@NotNull Event e) {
        DHAPI.createHologram(
                Objects.requireNonNull(name.getSingle(e)),
                Objects.requireNonNull(location.getSingle(e)),
                persistent, Arrays.asList(lines.getArray(e)));
        return super.walk(e, false);
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make a new hologram";
    }
}