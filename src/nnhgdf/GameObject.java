package nnhgdf;
import java.awt.geom.Rectangle2D;

public interface GameObject {
public Rectangle2D getCollitionShape();

public int getX();

public int getY();

public int getHeight();

public int getWidth();

public void paint();

public void update();

public void postCollition();

}
