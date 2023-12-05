package functions;

import java.awt.*;
import java.util.Iterator;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
public abstract class AbstractTabulatedFunction implements TabulatedFunction
{
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    public abstract Iterator<Point> iterator();

    static void checkLengthIsTheSame(double[] xValues, double[] yValues)
    {
        if (xValues == null || yValues == null) throw new DifferentLengthOfArraysException("Один или оба массива имеют значение null");
        if (xValues.length != yValues.length) throw new DifferentLengthOfArraysException("Массивы имеют различную длину");
    }

    static void checkSorted(double[] xValues)
    {
        if (xValues == null) throw new ArrayIsNotSortedException("Массив имеет значение null");
        if (xValues.length < 2) throw new ArrayIsNotSortedException("Массив содержит меньше 2 точек, недостаточно элементов для сортировки");
        for (int i = 0; i <xValues.length-1; i++) {
            if (xValues[i]>=xValues[i+1]) throw new ArrayIsNotSortedException("Массив не является отсортированным");
        }
    }
}

