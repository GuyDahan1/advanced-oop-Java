package Graphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * IDrawable interface
 */
public interface IDrawable{
    void loadImages(String nm) throws IOException;
    void drawObject(Graphics g);
}