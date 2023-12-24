package lol.aabss.skuishy.blueprints;

import org.mineskin.MineskinClient;
import org.mineskin.SkinOptions;
import org.mineskin.data.Skin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Mineskin {
    public static URL upload(BufferedImage image, String skinname) {
        try {
            MineskinClient client = new MineskinClient("MineskinJavaClient");
            final String name = "JavaClient-Upload";
            Skin skin = client.generateUpload(image, SkinOptions.name(skinname)).join();
            validateSkin(skin, name);
            return new URL(skin.data.texture.url);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    static void validateSkin(Skin skin, String name) {
        assertNotNull(skin);
        assertNotNull(skin.data);
        assertNotNull(skin.data.texture);

        assertEquals(name, skin.name);
    }
}
