
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SkinOptions {

    private static final String URL_FORMAT = "name=%s&model=%s&visibility=%s";

    private final String name;
    private final Variant variant;
    private final Visibility visibility;

    private SkinOptions(String name, Variant variant, Visibility visibility) {
        this.name = name;
        this.variant = variant;
        this.visibility = visibility;
    }

    @Deprecated
    protected String toUrlParam() {
        return String.format(URL_FORMAT, this.name, this.variant.getName(), this.visibility.getCode());
    }

    protected JsonObject toJson() {
        JsonObject json = new JsonObject();
        if (!Strings.isNullOrEmpty(name)) {
            json.addProperty("name", name);
        }
        if (variant != null && variant != Variant.AUTO) {
            json.addProperty("variant", variant.getName());
        }
        if (visibility != null) {
            json.addProperty("visibility", visibility.getCode());
        }
        return json;
    }

    protected void addAsData(HttpRequest.Builder builder) {
        Map<String, String> parameters = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            parameters.put("name", name);
        }
        if (variant != null && !variant.equals(Variant.AUTO)) {
            parameters.put("variant", variant.getName());
        }
        if (visibility != null) {
            parameters.put("visibility", String.valueOf(visibility));
        }

        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                    + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        String form = sj.toString();
        builder.POST(HttpRequest.BodyPublishers.ofString(form))
                .header("Content-Type", "application/x-www-form-urlencoded");
    }

    public String getName() {
        return name;
    }


    public static SkinOptions create(String name, Variant variant, Visibility visibility) {
        return new SkinOptions(name, variant, visibility);
    }

    public static SkinOptions name(String name) {
        return new SkinOptions(name, Variant.AUTO, Visibility.PUBLIC);
    }


    public static SkinOptions none() {
        return new SkinOptions("", Variant.AUTO, Visibility.PUBLIC);
    }

}