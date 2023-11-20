package functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;
class LinkedListTabulatedFunctionTest {

    double[] xValue = {1, 2, 3.3, 4.4, 5, 6};
    double[] yValue = {1, 2, 3, 4, 5, 6};
    double[] xValue2 = {6};
    double[] yValue2 = {2};
    double[] xValue3 = {7, 8};
    double[] yValue3 = {9, 7};
    LinkedListTabulatedFunction linkedListTabulatedFunction1= new LinkedListTabulatedFunction(xValue,yValue);
    LinkedListTabulatedFunction linkedListTabulatedFunction2= new LinkedListTabulatedFunction(xValue2,yValue2);
    LinkedListTabulatedFunction linkedListTabulatedFunction3=new LinkedListTabulatedFunction(xValue3,yValue3);
    @Test
    void getCount() {
        assertEquals(6, linkedListTabulatedFunction1.getCount());
        assertNotEquals(0, linkedListTabulatedFunction1.getCount());
    }
    @Test
    void leftBound() {
        assertEquals(1, linkedListTabulatedFunction1.leftBound());
        assertNotEquals(2, linkedListTabulatedFunction1.leftBound());
    }
    @Test
    void rightBound() {
        assertEquals(6, linkedListTabulatedFunction1.rightBound());
        assertNotEquals(1, linkedListTabulatedFunction1.rightBound());
    }
    @Test
    void getX() {
        assertEquals(1, linkedListTabulatedFunction1.getX(0));
        assertEquals(3.3, linkedListTabulatedFunction1.getX(2));
        assertNotEquals(0, linkedListTabulatedFunction1.getX(4));
    }
    @Test
    void getY() {
        assertEquals(1, linkedListTabulatedFunction1.getY(0));
        assertEquals(5, linkedListTabulatedFunction1.getY(4));
        assertNotEquals(0, linkedListTabulatedFunction1.getY(3));
    }
    @Test
    void setY() {
        linkedListTabulatedFunction1.setY(0, 0);
        assertEquals(0, linkedListTabulatedFunction1.getY(0));
        assertNotEquals(1, linkedListTabulatedFunction1.getY(0));
    }
    @Test
    void indexOfX() {
        assertEquals(0,linkedListTabulatedFunction1.indexOfX(1));
        assertNotEquals(4, linkedListTabulatedFunction1.indexOfX(1));
        assertEquals(-1, linkedListTabulatedFunction1.indexOfX(10));
        assertNotEquals(0, linkedListTabulatedFunction1.indexOfX(2.6));
    }
    @Test
    void indexOfY() {
        assertEquals(1, linkedListTabulatedFunction1.indexOfY(2));
        assertEquals(0, linkedListTabulatedFunction1.indexOfY(1));
        assertNotEquals(0, linkedListTabulatedFunction1.indexOfY(3));
        assertEquals(-1, linkedListTabulatedFunction1.indexOfY(7));
        assertNotEquals(0, linkedListTabulatedFunction1.indexOfY(7));
    }
    @Test
    void floorIndexOfX() {
        assertEquals(0, linkedListTabulatedFunction1.floorIndexOfX(0));
        assertNotEquals(2, linkedListTabulatedFunction1.floorIndexOfX(-1));
        assertEquals(6, linkedListTabulatedFunction1.floorIndexOfX(7));
        assertNotEquals(0, linkedListTabulatedFunction1.floorIndexOfX(6));
        assertEquals(0, linkedListTabulatedFunction1.floorIndexOfX(1.5));
        assertEquals(0, linkedListTabulatedFunction1.floorIndexOfX(1.7));
        assertEquals(2, linkedListTabulatedFunction1.floorIndexOfX(4));
        assertNotEquals(0, linkedListTabulatedFunction1.floorIndexOfX(2.5));
        assertEquals(6, linkedListTabulatedFunction1.floorIndexOfX(7));
        assertNotEquals(1, linkedListTabulatedFunction1.floorIndexOfX(7));
    }
    @Test
    void interpolate() {
        assertEquals(3,linkedListTabulatedFunction1.interpolate(3.3, 3));
        assertNotEquals(0, linkedListTabulatedFunction1.interpolate(2.7, 2));
        assertEquals(2, linkedListTabulatedFunction2.interpolate(2.7, 1));
        assertNotEquals(0, linkedListTabulatedFunction2.interpolate(2.7, 1));
    }
    @Test
    void extrapolateLeft() {
        assertEquals(0, linkedListTabulatedFunction1.extrapolateLeft(0));
        assertNotEquals(4, linkedListTabulatedFunction1.extrapolateLeft(0));
        assertEquals(2, linkedListTabulatedFunction2.extrapolateLeft(1));
        assertNotEquals(0, linkedListTabulatedFunction2.extrapolateLeft(1));
    }
    @Test
    void extrapolateRight() {
        assertEquals(5, linkedListTabulatedFunction1.extrapolateRight(5));
        assertNotEquals(0, linkedListTabulatedFunction1.extrapolateRight(10));
        assertEquals(2, linkedListTabulatedFunction2.extrapolateLeft(1));
        assertNotEquals(0, linkedListTabulatedFunction2.extrapolateLeft(1));
    }

    @Test
    void apply(){
        SqrFunction sqrFunction = new SqrFunction();
        LinkedListTabulatedFunction listTabulatedFunction = new LinkedListTabulatedFunction(sqrFunction, 10,100,90);
        assertEquals(57.97752808988765, listTabulatedFunction.apply(8));
        assertEquals(78.98876404494382, listTabulatedFunction.apply(9));

    }
    @Test
    void nodeToString(){
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(4, -5);
        assertEquals("(4.0; -5.0)", node.toString());
        assertNotEquals("(4.0; 5.0)", node.toString());
    }
    @Test
    void nodeEquals(){
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(4, -5);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(4, 5);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(4, -5);

        assertTrue(node1.equals(node1));
        assertTrue(node1.equals(node3));
        assertTrue(node3.equals(node1));
        assertFalse(node1.equals(node2));
    }
    @Test
    void nodeHash()
    {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(4, -5);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(4, 5);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(4, -5);

        assertEquals(node1.hashCode(), node3.hashCode());
        assertNotEquals(node2.hashCode(), node3.hashCode());
    }
    @Test
    void nodeClone() throws CloneNotSupportedException {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(4, -5);
        Object clone = node.clone();
        assertTrue(node.equals(clone));
    }

}