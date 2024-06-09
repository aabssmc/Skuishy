
//
// -----------------------------------------------------
// https://github.com/InventivetalentDev/MineskinClient
// ALL CREDIT TO InventivetalentDev FOR ALL THIS CODE
// https://github.com/InventivetalentDev
// -----------------------------------------------------
//

package lol.aabss.skuishy.other.mineskin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lol.aabss.skuishy.other.mineskin.data.MineskinException;
import lol.aabss.skuishy.other.mineskin.data.Skin;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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

    private HttpRequest.Builder generateRequest(String endpoint) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(GENERATE_BASE + endpoint))
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("User-Agent", userAgent)
                .timeout(java.time.Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        if (apiKey != null) {
            builder.header("Authorization", "Bearer " + apiKey);
        }

        return builder;
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
                HttpRequest.Builder request = generateRequest("/url")
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body.toString()));
                return handleResponse(request);
            } catch (Exception e) {
                throw new RuntimeException(e);
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

                byte[] fileBytes = inputStreamToByteArray(is);

                String boundary = "Boundary-" + System.currentTimeMillis();
                String multipartBody = buildMultipartBody(boundary, fileBytes, name, options);

                HttpRequest.Builder request = generateRequest("/upload")
                        .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                        .POST(HttpRequest.BodyPublishers.ofString(multipartBody));

                return handleResponse(request);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, requestExecutor);
    }

    private byte[] inputStreamToByteArray(InputStream is) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }

    private String buildMultipartBody(String boundary, byte[] fileBytes, String fileName, SkinOptions options) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(fileName).append("\"\r\n");
        sb.append("Content-Type: application/octet-stream\r\n\r\n");
        sb.append(new String(fileBytes, StandardCharsets.UTF_8)).append("\r\n");

        JsonObject body = options.toJson();
        for (Map.Entry<String, JsonElement> entry : body.entrySet()) {
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"\r\n\r\n");
            sb.append(entry.getValue().getAsString()).append("\r\n");
        }

        sb.append("--").append(boundary).append("--\r\n");
        return sb.toString();
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


    Skin handleResponse(HttpRequest.Builder request) throws MineskinException, JsonParseException, IOException, InterruptedException {
        JsonObject jsonObject = gson.fromJson(HttpClient.newHttpClient().send(request.build(), HttpResponse.BodyHandlers.ofString()).body(), JsonObject.class);
        if (jsonObject.has("error")) {
            throw new MineskinException(jsonObject.get("error").getAsString());
        }
        Skin skin = gson.fromJson(jsonObject, Skin.class);
        this.nextRequest = System.currentTimeMillis() + ((long) (skin.delayInfo.millis + (this.apiKey == null ? 10_000 : 100)));
        return skin;
    }

}