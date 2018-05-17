import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class TextureMapper {
    private TextureMapper() {}

    private static final Map<Long, BufferedImage> entityIdToTexture = new HashMap<>(
            Settings.TEXTURE_MAPPER_INITIAL_CAPACITY);

    public static void put(long id, BufferedImage texture) {
        entityIdToTexture.put(id, texture);
    }

    public static BufferedImage get(long id) {
        return entityIdToTexture.get(id);
    }
}
