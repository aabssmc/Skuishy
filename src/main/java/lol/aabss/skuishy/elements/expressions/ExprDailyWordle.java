package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.Nullable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Name("Other - Daily Wordle")
@Description("The daily wordle.")
@Examples({
        "send daily wordle"
})
@Since("1.6")

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
        AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String url = "https://www.nytimes.com/svc/wordle/v2/" + df.format(date) + ".json";
        final String[] answer = new String[1];
        asyncHttpClient
                .prepareGet(url)
                .execute(new AsyncCompletionHandler<Response>() {
                    @Override
                    public Response onCompleted(Response response) throws Exception {
                        String body = response.getResponseBody();
                        JSONParser parser = new JSONParser();
                        JSONObject ea = (JSONObject) parser.parse(body);
                        answer[0] = ea.get("solution").toString();
                        return response;
                    }

                    @Override
                    public void onThrowable(Throwable t) {
                        t.printStackTrace();
                    }
                });
        return answer;
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
