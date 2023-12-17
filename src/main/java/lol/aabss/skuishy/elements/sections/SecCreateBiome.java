package lol.aabss.skuishy.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Section;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URL;
import java.util.List;

import static lol.aabss.skuishy.other.GetVersion.datapackVersion;
import static lol.aabss.skuishy.other.GetVersion.version;

@Name("Biome - Create Biome")
@Examples({
        "some event:",
        "\tcreate a new biome for player's world:",
        "\t\tname: \"test\"",
        "\t\ttemperature: 0",
        "\t\ttemperature modifier: none",
        "\t\tdownfall: 0",
        "\t\tsky color: red",
        "\t\tfog color: white",
        "\t\twater color: yellow",
        "\t\twater fog color: orange",
        "\t\tgrass color: purple",
        "\t\tfoliage color: blue",
        "\t\tgrass color modifier: swamp"
})
@Description("Creates a new biome\n# REQUIRES 1.19+ & ALL COLOR ARE HEX CODES (without #s)")
@Since("1.9")

public class SecCreateBiome extends Section {

    // TODO: add biome spawners & placed features
    public enum GrassColorModifier{
        NONE,
        DARK_FOREST,
        SWAMP;
    }

    public enum TemperatureModifier{
        NONE,
        FROZEN;
    }

    private static final EntryValidator.EntryValidatorBuilder ENTRY_VALIDATOR = EntryValidator.builder();

    static {
        Skript.registerSection(SecCreateBiome.class,
                "(create|make) [a] [new] biome for %world%"
        );
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("name", null, false, String.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("temperature", null, false, Integer.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("temperature modifier", null, false, TemperatureModifier.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("downfall", null, false, Integer.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("sky color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("fog color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("water color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("water fog color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("grass color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("foliage color", null, false, ch.njol.skript.util.Color.class));
        ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("grass color modifier", null, true, GrassColorModifier.class));

    }

    private Expression<String> name;
    private Expression<World> world;
    private Expression<Integer> temp;
    private Expression<TemperatureModifier> tempmod;
    private Expression<Integer> downfall;
    private Expression<ch.njol.skript.util.Color> skycolor;
    private Expression<ch.njol.skript.util.Color> fogcolor;
    private Expression<ch.njol.skript.util.Color> watercolor;
    private Expression<ch.njol.skript.util.Color> waterfogcolor;
    private Expression<ch.njol.skript.util.Color> grasscolor;
    private Expression<ch.njol.skript.util.Color> foliagecolor;
    private Expression<GrassColorModifier> grasscolormod;

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
        world = (Expression<World>) exprs[0];
        name = (Expression<String>) container.getOptional("name", false);
        temp = (Expression<Integer>) container.getOptional("temperature", false);
        tempmod = (Expression<TemperatureModifier>) container.getOptional("temperature modifier", false);
        downfall = (Expression<Integer>) container.getOptional("downfall", false);
        skycolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("sky color", false);
        fogcolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("fog color", false);
        watercolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("water color", false);
        waterfogcolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("water fog color", false);
        grasscolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("grass color", false);
        foliagecolor = (Expression<ch.njol.skript.util.Color>) container.getOptional("foliage color", false);
        grasscolormod = (Expression<GrassColorModifier>) container.getOptional("grass color modifier", false);
        return name != null;
    }

    @Override
    protected @Nullable TriggerItem walk(@NotNull Event e) {
        try {
            File file = new File(world.getSingle(e).getWorldFolder() + "/datapacks/skuishy/");
            if (file.exists()){
                File dir = new File(file, "data/skuishy/worldgen/biome");
                if (dir.mkdirs()){
                    File biome = new File(dir, name.getSingle(e) + ".json");
                    if (biome.createNewFile()){
                        biome(e, biome);
                    }
                }
            }
            else{
                if (file.mkdir()){
                    Bukkit.getLogger().info("good");
                }
                File format = new File(file, "pack.mcmeta");
                if (format.createNewFile()){
                    FileWriter formatwrite = new FileWriter(format);
                    URL url = new URL("https://aabssmc.github.io/pack.mcmeta");
                    formatwrite.write(readURL(url)
                            .replaceAll("FORMAT", datapackVersion()));
                    formatwrite.close();
                    File dir = new File(file, "data/skuishy/worldgen/biome");
                    if (dir.mkdirs()){
                        File biome = new File(dir, name.getSingle(e) + ".json");
                        if (biome.createNewFile()){
                            biome(e, biome);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return super.walk(e, false);
    }

    private void biome(@NotNull Event e, File biome) throws IOException {
        FileWriter biomewrite = new FileWriter(biome);
        URL url = new URL("https://aabssmc.github.io/datapacks/biome/biome-" + version() + ".json");
        String content = readURL(url);
        content = content.replaceAll("\"temperature\": 0", "\"temperature\": " + temp.getSingle(e))
                .replaceAll("\"temperature_modifier\": \"none\"", "\"temperature_modifier\": \"" + tempmod.getSingle(e).name().toLowerCase() + "\"")
                .replaceAll("\"downfall\": 0", "\"downfall\": " + downfall.getSingle(e))
                .replaceAll("\"sky_color\": 0", "\"sky_color\": " + skycolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"fog_color\": 0", "\"fog_color\": " + fogcolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"water_color\": 0", "\"water_color\": " + watercolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"water_fog_color\": 0", "\"water_fog_color\": " + waterfogcolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"grass_color\": 0", "\"grass_color\": " + grasscolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"foliage_color\": 0", "\"foliage_color\": " + foliagecolor.getSingle(e).asBukkitColor().asRGB())
                .replaceAll("\"grass_color_modifier\": \"none\"", "\"grass_color_modifier\": \"" + grasscolormod.getSingle(e).name().toLowerCase() + "\"")
        ;
        biomewrite.write(content);
        biomewrite.close();
    }

    private String readURL(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }
        bufferedReader.close();
        return stringBuilder.toString().trim();
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make a new biome";
    }
}
