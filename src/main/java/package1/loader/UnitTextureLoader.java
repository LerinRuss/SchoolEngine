package package1.loader;

import package1.exception.LoaderTexturesException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UnitTextureLoader {
    private BufferedImage test_unit;

    public void loadTextures() throws LoaderTexturesException {
        try {
            test_unit = ImageIO.read(new File("textures/units/test_unit/stand.png"));
        } catch (IOException e) {
            throw new LoaderTexturesException("Ошибка загрузки текстур");
        }

    }
    public BufferedImage getTestUnit(){
        return test_unit;
    }
}
