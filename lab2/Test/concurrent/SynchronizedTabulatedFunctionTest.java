package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {
    double[] xValue = {1, 2, 2.7, 2.9, 5};
    double[] yValue = {1, 2, 3, 4, 5};
    LinkedListTabulatedFunction linkedListTabulatedFunction=new LinkedListTabulatedFunction(xValue,yValue);
    SynchronizedTabulatedFunction STF = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);

    @Test
    void apply() {
        assertEquals(2, STF.apply(2));
        assertEquals(3, STF.apply(2.7));
        assertNotEquals(0, STF.apply(2.9));
    }

    @Test
    void getCount() {
        assertEquals(5, STF.getCount());
        assertNotEquals(0, STF.getCount());
    }

    @Test
    void getX() {
        assertEquals(2, STF.getX(1));
        assertEquals(2.9, STF.getX(3));
        assertNotEquals(0, STF.getX(2));
    }

    @Test
    void getY() {
        assertEquals(1, STF.getY(0));
        assertEquals(4, STF.getY(3));
        assertNotEquals(0, STF.getY(3));
    }

    @Test
    void setY() {
        STF.setY(0, 0);
        assertEquals(0, STF.getY(0));
        assertNotEquals(1,STF.getY(0));
    }

    @Test
    void indexOfX() {
        assertEquals(2,STF.indexOfX(2.7));
        assertNotEquals(4, STF.indexOfX(2.9));
    }

    @Test
    void indexOfY() {
        assertEquals(1, STF.indexOfY(2));
        assertEquals(0, STF.indexOfY(1));
        assertNotEquals(0, STF.indexOfY(3));
    }

    @Test
    void leftBound() {
        assertEquals(1, STF.leftBound());
        assertNotEquals(0, STF.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(5, STF.rightBound());
        assertNotEquals(0, STF.rightBound());
    }

    @Test
    void iterator() {
        Iterator<Point> iterator = STF.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }
    }

    @Test
    void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operationDouble = func -> {
            double maxNumber = STF.getY(0);
            for (Point point : STF)
                if (maxNumber < point.y) maxNumber = point.y;
            return maxNumber;
        };
        SynchronizedTabulatedFunction.Operation<Void> operationVoid = func -> {
            for (Point point : STF)
                System.out.println(point.x + " " + point.y);
            return null;
        };
        assertNull(STF.doSynchronously(operationVoid));
        assertEquals(5, STF.doSynchronously(operationDouble));
    }
}