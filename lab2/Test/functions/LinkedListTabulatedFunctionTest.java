package functions;

import functions.LinkedListTabulatedFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
class LinkedListTabulatedFunctionTest {

    double[] xValue = {1, 2, 3.3, 4.4, 5, 6};
    double[] yValue = {1, 2, 3, 4, 5, 6};
    double[] xValue3 = {7, 8};
    double[] yValue3 = {9, 7};
    LinkedListTabulatedFunction linkedListTabulatedFunction1= new LinkedListTabulatedFunction(xValue,yValue);
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
    }
    @Test
    void indexOfY() {
        assertEquals(1, linkedListTabulatedFunction1.indexOfY(2));
        assertEquals(0, linkedListTabulatedFunction1.indexOfY(1));
        assertNotEquals(0, linkedListTabulatedFunction1.indexOfY(3));
    }
    @Test
    void floorIndexOfX() {
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
    }
    @Test
    void extrapolateLeft() {
        assertEquals(0, linkedListTabulatedFunction1.extrapolateLeft(0));
        assertNotEquals(4, linkedListTabulatedFunction1.extrapolateLeft(0));
    }
    @Test
    void extrapolateRight() {
        assertEquals(5, linkedListTabulatedFunction1.extrapolateRight(5));
        assertNotEquals(0, linkedListTabulatedFunction1.extrapolateRight(10));
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
    @Test
    void equalsList(){
        double[] xValue11 = {1, 2, 3.3, 4.4, 5, 6};
        double[] yValue11 = {1, 2, 3, 4, 5, 6};
        double[] xValue22 = {1, 2};
        double[] yValue22 = {1, 2};
        LinkedListTabulatedFunction linkedListTabulatedFunction11 = new LinkedListTabulatedFunction(xValue11,yValue11);
        LinkedListTabulatedFunction linkedListTabulatedFunction22 = new LinkedListTabulatedFunction(xValue22,yValue22);
        assertTrue(linkedListTabulatedFunction1.equals(linkedListTabulatedFunction11));
        assertTrue(linkedListTabulatedFunction1.equals(linkedListTabulatedFunction1));
        assertFalse(linkedListTabulatedFunction1.equals(linkedListTabulatedFunction22));
    }
    @Test
    void hashList(){
        double[] xValue11 = {1, 2, 3.3, 4.4, 5, 6};
        double[] yValue11 = {1, 2, 3, 4, 5, 6};
        LinkedListTabulatedFunction linkedListTabulatedFunction11 = new LinkedListTabulatedFunction(xValue11,yValue11);
        assertEquals(linkedListTabulatedFunction1.hashCode(), linkedListTabulatedFunction11.hashCode());

    }

    @Test
    void cloneList(){
        Object test = linkedListTabulatedFunction1.clone();
        assertTrue(linkedListTabulatedFunction1.equals(test));
    }
    @Test
    void constructorOne(){
        double[] xValue2 = {5};
        double[] yValue2 = {2};
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(xValue2, yValue2);
        });
    }

    @Test
    void constructorTwo(){
        cos2xFunctions cos = new cos2xFunctions();
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(cos,1,2,1);
        });
    }
    @Test
    void getNodeEx(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.getNode(-1);
        });
    }
    void getXex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.getX(15);
        });
    }
    @Test
    void getYex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.getY(-15);
        });
    }
    @Test
    void setYex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.setY(11, 11);
        });
    }
    @Test
    void floorIndexOfXex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.floorIndexOfX(0);
        });
    }
    @Test
    void indexOfXex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.indexOfX(0);
        });
    }
    @Test
    void indexOfYex(){
        assertThrows(IllegalArgumentException.class, () ->{
            linkedListTabulatedFunction1.indexOfY(9);
        });
    }

    @Test
    void iterator() {
        Iterator<Point> iterator = linkedListTabulatedFunction1.iterator();
        LinkedListTabulatedFunction.Node node = linkedListTabulatedFunction1.getNode(0);
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, linkedListTabulatedFunction1.getX(i));
            assertEquals(point.y, linkedListTabulatedFunction1.getY(i));
            i++;
        }
        i = 0;
        for (Point point : linkedListTabulatedFunction1){
            assertEquals(linkedListTabulatedFunction1.getX(i),point.x);
            assertEquals(linkedListTabulatedFunction1.getY(i),point.y);
            i++;
        }
    }
    @Test
    void newToString(){
        assertEquals(linkedListTabulatedFunction1.toString(), "LinkedListTabulatedFunction size = 6\n[1.0; 1.0]\n[2.0; 2.0]\n[3.3; 3.0]\n[4.4; 4.0]\n[5.0; 5.0]\n[6.0; 6.0]");
    }
}