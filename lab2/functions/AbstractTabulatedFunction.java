package functions;
import java.awt.*;
import java.util.Iterator;
public abstract class AbstractTabulatedFunction implements TabulatedFunction
{
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    public abstract Iterator<Point> iterator();
}
