
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.mineskin.data.MineskinException;
import lol.aabss.skuishy.other.mineskin.data.Skin;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.checkNotNull;

public class MineskinClient {

    private static final String API_BASE = "https://api.mineskin.org";
    private static final String GENERATE_BASE = API_BASE + "/generate";
    private static final String GET_BASE = API_BASE + "/get";

    private final Executor requestExecutor;
    private final String userAgent;
    private final String apiKey;

    private final Gson gson = new Gson();

    private long nextRequest = 0;


    public MineskinClient(String userAgent) {
        this.requestExecutor = Executors.newSingleThreadExecutor();
        this.userAgent = checkNotNull(userAgent);
        this.apiKey = null;
    }

    public MineskinClient(String userAgent, String apiKey) {
        this.requestExecutor = Executors.newSingleThreadExecutor();
        this.userAgent = checkNotNull(userAgent);
        this.apiKey = apiKey;
    }

    public MineskinClient(Executor requestExecutor, String userAgent, String apiKey) {
        this.requestExecutor = checkNotNull(requestExecutor);
        this.userAgent = checkNotNull(userAgent);
        this.apiKey = apiKey;
    }

    public MineskinClient(Executor requestExecutor, String userAgent) {
        this.requestExecutor = checkNotNull(requestExecutor);
        this.userAgent = checkNotNull(userAgent);
        this.apiKey = null;
    }

    public long getNextRequest() {
        return nextRequest;
    }

    private Connection generateRequest(String endpoint) {
        Connection connection = Jsoup.connect(GENERATE_BASE + endpoint)
                .method(Connection.Method.POST)
                .userAgent(userAgent)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(30000);
        if (apiKey != null) {
            connection.header("Authorization", "Bearer " + apiKey);
        }
        return connection;
    }

    /**
     * Generates skin data from an URL
     */
    public CompletableFuture<Skin> generateUrl(String url, SkinOptions options) {
        checkNotNull(url);
        checkNotNull(options);
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (System.currentTimeMillis() < nextRequest) {
                    long delay = (nextRequest - System.currentTimeMillis());
                    Thread.sleep(delay + 10);
                }

                JsonObject body = options.toJson();
                body.addProperty("url", url);
                Connection connection = generateRequest("/url")
                        .header("Content-Type", "application/json")
                        .requestBody(body.toString());
                return handleResponse(connection.execute().body());
            } catch (Exception e) {
                Skuishy.Logger.exception(e);
                return null;
            }
        }, requestExecutor);
    }


    public CompletableFuture<Skin> generateUpload(InputStream is, SkinOptions options) {
        return generateUpload(is, options, options.getName() + ".png");
    }

    public CompletableFuture<Skin> generateUpload(InputStream is, SkinOptions options, String name) {
        checkNotNull(is);
        checkNotNull(options);
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (System.currentTimeMillis() < nextRequest) {
                    long delay = (nextRequest - System.currentTimeMillis());
                    Thread.sleep(delay + 10);
                }

                Connection connection = generateRequest("/upload")
                        // It really doesn't like setting a content-type header here for some reason
                        .data("file", name, is);
                options.addAsData(connection);
                return handleResponse(connection.execute().body());
            } catch (Exception e) {
                Skuishy.Logger.exception(e);
                return null;
            }
        }, requestExecutor);
    }

    /**
     * Uploads and generates skin data from a local file (with default options)
     */

    public CompletableFuture<Skin> generateUpload(File file, SkinOptions options) throws FileNotFoundException {
        checkNotNull(file);
        checkNotNull(options);
        return generateUpload(new FileInputStream(file), options, file.getName());
    }

    /**
     * Uploads and generates skin data from a RenderedImage object (with default options)
     */
    public CompletableFuture<Skin> generateUpload(RenderedImage image, SkinOptions options) throws IOException {
        checkNotNull(image);
        checkNotNull(options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return generateUpload(new ByteArrayInputStream(baos.toByteArray()), options);
    }


    Skin handleResponse(String body) throws MineskinException, JsonParseException {
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        if (jsonObject.has("error")) {
            Skuishy.Logger.exception(MineskinException.class, jsonObject.get("error").getAsString());
        }
        Skin skin = gson.fromJson(jsonObject, Skin.class);
        this.nextRequest = System.currentTimeMillis() + ((long) (skin.delayInfo.millis + (this.apiKey == null ? 10_000 : 100)));
        return skin;
    }

}