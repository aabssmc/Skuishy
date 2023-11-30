package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.annotation.Nullable;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ExprDailyWordle extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprDailyWordle.class, String.class, ExpressionType.SIMPLE,
                "[the] [daily] wordle [answer]",
                "today's [daily] wordle [answer]",
                "[the] wordle [answer] of today"
        );
    }

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
        try {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            String url = "https://www.nytimes.com/svc/wordle/v2/" + df.format(date) + ".json";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get().
                    build();
            assert request.body() != null;
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONObject ea = (JSONObject) parser.parse(response.toString());
            return new String[]{ea.get("solution").toString()};
        } catch (ParseException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "daily wordle answer";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}