package functions;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Iterator;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Cloneable{

    protected double [] xValues;

    protected double [] yValues;

    protected int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues){
        if(xValues.length < 2) throw new IllegalArgumentException("недопустимое значение");
        else {
            this.count = xValues.length;
            this.xValues = Arrays.copyOf(xValues, xValues.length);
            this.yValues = Arrays.copyOf(yValues, yValues.length);
        }
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if(count < 2) throw new IllegalArgumentException("недопустимое значение");
        else {
            this.count = count;
            this.xValues = new double[count];
            this.yValues = new double[count];
            if (xFrom < xTo) {
                this.xValues[0] = xFrom;
                this.xValues[count - 1] = xTo;
            } else {
                this.xValues[0] = xTo;
                this.xValues[count - 1] = xFrom;
            }
            double delta = (this.xValues[count - 1] - this.xValues[0]) / (count - 1);
            this.yValues[0] = source.apply(this.xValues[0]);
            this.yValues[count - 1] = source.apply(this.xValues[count - 1]);
            for (int i = 1; i < count; i++) {
                this.xValues[i] = this.xValues[i - 1] + delta;
                this.yValues[i] = source.apply(this.xValues[i]);
            }
        }
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < this.leftBound()) throw new IllegalArgumentException("недопустимое значение");
        if (x > this.rightBound()) return count-1;

        int leftBorder = 0;
        int rightBorder = this.xValues.length - 1;
        while (leftBorder <= rightBorder) {
            int middle = (leftBorder + rightBorder) / 2;
            double current = this.xValues[middle];
            if (current == x) {
                return middle;
            } else if (current < x) {
                leftBorder = middle + 1;
            } else {
                rightBorder = middle - 1;
            }
        }
        return leftBorder-1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return yValues[1]+(((x-xValues[1])/(xValues[0]-xValues[1]))*(yValues[0]-yValues[1]));
    }

    @Override
    protected double extrapolateRight(double x) {
        return yValues[count-2]+(((x-xValues[count-2])/(xValues[count-1]-xValues[count-2]))*(yValues[count-1]-yValues[count-2]));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return yValues[floorIndex]+((yValues[floorIndex+1]-yValues[floorIndex])/(xValues[floorIndex+1]-xValues[floorIndex]))*(x-xValues[floorIndex]);
    }

        @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY+((rightY-leftY)/(rightX-leftX))*(x-leftX);
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= this.count) throw new IllegalArgumentException("недопустимое значение");
        return this.xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= this.count) throw new IllegalArgumentException("недопустимое значение");
        return this.yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if(index < 0 || index >= this.count) throw new IllegalArgumentException("недопустимое значение");
        this.yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        if ((x < this.leftBound()) || (x > this.rightBound())) throw new IllegalArgumentException("недопустимое значение");
        int leftBorder = 0;
        int rightBorder = this.xValues.length - 1;
        while (leftBorder <= rightBorder) {
            int middle = (leftBorder + rightBorder) / 2;
            double current = this.xValues[middle];

            if (current == x) {
                return middle;
            } else if (current < x) {
                leftBorder = middle + 1;
            } else {
                rightBorder = middle - 1;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if ((y < this.getY(0)) || (y > this.getY(this.count-1))) throw new IllegalArgumentException("недопустимое значение");
        int leftBorder = 0;
        int rightBorder = this.yValues.length - 1;
        while (leftBorder <= rightBorder) {
            int middle = (leftBorder + rightBorder) / 2;
            double current = this.yValues[middle];

            if (current == y) {
                return middle;
            } else if (current < y) {
                leftBorder = middle + 1;
            } else {
                rightBorder = middle - 1;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return this.xValues[0];
    }

    @Override
    public double rightBound() {
        return this.xValues[this.count-1];
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) != -1) return getY(indexOfX(x));
        else return interpolate(x,floorIndexOfX(x));
    }

    @Override
    public String toString() {
        return "{xValues=" + Arrays.toString(xValues) +
                ", yValues=" + Arrays.toString(yValues) +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ArrayTabulatedFunction) || (((ArrayTabulatedFunction) obj).count != this.count)) return false;
        for (int i = 0 ; i < this.count; ++i) {
            if (this.xValues[i] != ((ArrayTabulatedFunction) obj).xValues[i]) return false;
            if (this.yValues[i] != ((ArrayTabulatedFunction) obj).yValues[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xValues,yValues,count);
    }

    @Override
    public Object clone() {
        return new ArrayTabulatedFunction(this.xValues, this.yValues);
    }
    @Override
    public Iterator<Point> iterator()
    {
        throw new UnsupportedOperationException();
    }
}