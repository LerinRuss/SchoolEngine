package loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureLoader {
    private TextureLoader() {}

    public static TextureStorage load(String[] files, String dirPath, String format)
            throws LoaderTexturesException {

        final File absolute = new File("").getAbsoluteFile();
        final File dir = new File(absolute, dirPath);

        final BufferedImage[] textures = new BufferedImage[files.length];

        for (int i = 0; i < files.length; i++) {
            final File path = new File(dir, files[i].concat(format));
            try {
                final BufferedImage texture = ImageIO.read(path);
                textures[i] = texture;
            } catch (IOException e) {
                throw new LoaderTexturesException(
                        ErrorMessage.FAIL_LOAD_TEXTURE.format(path.toString()), e);
            }

        }


        return new TextureStorage(textures);
    }
}
