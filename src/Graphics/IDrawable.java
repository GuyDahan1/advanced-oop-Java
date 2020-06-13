package Graphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * IDrawable interface
 */
public interface IDrawable{
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString()+"\\src\\Graphics\\Icons\\";
    public final static String PICTURE_PATH = s;
    public void loadImages(String nm) throws IOException;
    public void drawObject (Graphics g);
}