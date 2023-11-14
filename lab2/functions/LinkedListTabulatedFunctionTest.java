package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    double[] xValue = {1, 2, 3.3, 4.4, 5, 6};
    double[] yValue = {1, 2, 3, 4, 5, 6};
    double[] xValue2 = {6};
    double[] yValue2 = {2};
    LinkedListTabulatedFunction linkedListTabulatedFunction= new LinkedListTabulatedFunction(xValue,yValue);
    LinkedListTabulatedFunction linkedListTabulatedFunction1= new LinkedListTabulatedFunction(xValue2,yValue2);
    @Test
    void getCount() {
        assertEquals(6, linkedListTabulatedFunction.getCount());
        assertNotEquals(0, linkedListTabulatedFunction.getCount());
    }
    @Test
    void leftBound() {
        assertEquals(1, linkedListTabulatedFunction.leftBound());
        assertNotEquals(2, linkedListTabulatedFunction.leftBound());
    }
    @Test
    void rightBound() {
        assertEquals(6, linkedListTabulatedFunction.rightBound());
        assertNotEquals(1, linkedListTabulatedFunction.rightBound());
    }
    @Test
    void getX() {
        assertEquals(1, linkedListTabulatedFunction.getX(0));
        assertEquals(3.3, linkedListTabulatedFunction.getX(2));
        assertNotEquals(0, linkedListTabulatedFunction.getX(4));
    }
    @Test
    void getY() {
        assertEquals(1, linkedListTabulatedFunction.getY(0));
        assertEquals(5, linkedListTabulatedFunction.getY(4));
        assertNotEquals(0, linkedListTabulatedFunction.getY(3));
    }
    @Test
    void setY() {
        linkedListTabulatedFunction.setY(0, 0);
        assertEquals(0, linkedListTabulatedFunction.getY(0));
        assertNotEquals(1, linkedListTabulatedFunction.getY(0));
    }
    @Test
    void indexOfX() {
        assertEquals(0,linkedListTabulatedFunction.indexOfX(1));
        assertNotEquals(4, linkedListTabulatedFunction.indexOfX(1));
        assertEquals(-1, linkedListTabulatedFunction.indexOfX(10));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfX(2.6));
    }
    @Test
    void indexOfY() {
        assertEquals(1, linkedListTabulatedFunction.indexOfY(2));
        assertEquals(0, linkedListTabulatedFunction.indexOfY(1));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfY(3));
        assertEquals(-1, linkedListTabulatedFunction.indexOfY(7));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfY(7));
    }
    @Test
    void floorIndexOfX() {
        assertEquals(0, linkedListTabulatedFunction.floorIndexOfX(0));
        assertNotEquals(2, linkedListTabulatedFunction.floorIndexOfX(-1));
        assertEquals(6, linkedListTabulatedFunction.floorIndexOfX(7));
        assertNotEquals(0, linkedListTabulatedFunction.floorIndexOfX(6));
        assertEquals(0, linkedListTabulatedFunction.floorIndexOfX(1.5));
        assertEquals(0, linkedListTabulatedFunction.floorIndexOfX(1.7));
        assertEquals(2, linkedListTabulatedFunction.floorIndexOfX(4));
        assertNotEquals(0, linkedListTabulatedFunction.floorIndexOfX(2.5));
        assertEquals(6, linkedListTabulatedFunction.floorIndexOfX(7));
        assertNotEquals(1, linkedListTabulatedFunction.floorIndexOfX(7));
    }
    @Test
    void interpolate() {
        assertEquals(3,linkedListTabulatedFunction.interpolate(3.3, 3));
        assertNotEquals(0, linkedListTabulatedFunction.interpolate(2.7, 2));
        assertEquals(2, linkedListTabulatedFunction1.interpolate(2.7, 1));
        assertNotEquals(0, linkedListTabulatedFunction1.interpolate(2.7, 1));
    }
    @Test
    void extrapolateLeft() {
        assertEquals(0, linkedListTabulatedFunction.extrapolateLeft(0));
        assertNotEquals(4, linkedListTabulatedFunction.extrapolateLeft(0));
        assertEquals(2, linkedListTabulatedFunction1.extrapolateLeft(1));
        assertNotEquals(0, linkedListTabulatedFunction1.extrapolateLeft(1));
    }
    @Test
    void extrapolateRight() {
        assertEquals(5, linkedListTabulatedFunction.extrapolateRight(5));
        assertNotEquals(0, linkedListTabulatedFunction.extrapolateRight(10));
        assertEquals(2, linkedListTabulatedFunction1.extrapolateLeft(1));
        assertNotEquals(0, linkedListTabulatedFunction1.extrapolateLeft(1));
    }

    @Test
    void apply(){
        SqrFunction sqrFunction = new SqrFunction();
        LinkedListTabulatedFunction listTabulatedFunction = new LinkedListTabulatedFunction(sqrFunction, 10,100,90);
        assertEquals(10198.988764044945, listTabulatedFunction.apply(101));
        assertEquals(78.9887640449438, listTabulatedFunction.apply(9));

    }
}