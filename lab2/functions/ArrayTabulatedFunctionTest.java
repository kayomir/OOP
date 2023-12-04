package functions;

import functions.ArrayTabulatedFunction;
import functions.SqrFunction;
import functions.cos2xFunctions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void ArrayTabulatedFunction() {
        double[] X = {1,2,3,4,5,6,7,8,9,10};
        double[] Y = {1,4,9,16,25,36,49,64,81,100};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertEquals(4,ATF.apply(2));
        assertEquals(36,ATF.apply(6));
        assertEquals(81,ATF.apply(9));
    }

    @Test
    void floorIndexOfX() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,100,100);
        assertEquals(15,ATF.floorIndexOfX(16.5));
        assertEquals(99,ATF.floorIndexOfX(110));
    }

    @Test
    void extrapolateLeft() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 10,20,11);
        assertEquals(81,ATF.extrapolateLeft(9),81*0.05);
    }

    @Test
    void extrapolateRight() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(121,ATF.extrapolateRight(11),121*0.05);
        assertEquals(144,ATF.extrapolateRight(12),138*0.05);
    }

    @Test
    void interpolate() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(30.25,ATF.interpolate(5.5,ATF.floorIndexOfX(5.5)), 30.25*0.05);
        assertEquals(72.25,ATF.interpolate(8.5,ATF.floorIndexOfX(8.5)),72.25*0.05);
        assertEquals(ATF.interpolate(5.5,ATF.floorIndexOfX(5.5)),ATF.interpolate(5.5,ATF.getX(4),ATF.getX(5),ATF.getY(4),ATF.getY(5)));
        assertEquals(ATF.interpolate(8.5,ATF.floorIndexOfX(8.5)),ATF.interpolate(8.5,ATF.getX(7),ATF.getX(8),ATF.getY(7),ATF.getY(8)));

    }

    @Test
    void getCount() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(10,ATF.getCount());
    }

    @Test
    void getX() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(5,ATF.getX(4));
        assertEquals(8,ATF.getX(7));
        assertEquals(2,ATF.getX(1));
    }

    @Test
    void getY() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(25,ATF.getY(4));
        assertEquals(64,ATF.getY(7));
        assertEquals(4,ATF.getY(1));
    }

    @Test
    void setY() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(25,ATF.getY(4));
        ATF.setY(4,36);
        assertEquals(36,ATF.getY(4));
    }

    @Test
    void indexOfX() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(1,ATF.indexOfX(2));
        assertEquals(6,ATF.indexOfX(7));
    }

    @Test
    void indexOfY() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(1,ATF.indexOfY(4));
        assertEquals(6,ATF.indexOfY(49));
    }

    @Test
    void leftBound() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(ATF.xValues[0],ATF.leftBound());
    }

    @Test
    void rightBound() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(ATF.xValues[ATF.getCount()-1],ATF.rightBound());
    }

    @Test
    void apply() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 10,100,90);
        assertEquals(10201,ATF.apply(101),10201*0.05);
        assertEquals(81,ATF.apply(9),81*0.05);
        assertEquals(2070.25,ATF.apply(45.5), 2070.25*0.05);
    }

    @Test
    void testToString() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        System.out.println(ATF.toString());
    }


    @Test
    void testEquals() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction firstArrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        double[] X = {1,2,3,4,5,6,7,8,9,10};
        double[] Y = {1,4,9,16,25,36,49,64,81,100};
        ArrayTabulatedFunction secondArrayTabulatedFunction = new ArrayTabulatedFunction(X,Y);
        ArrayTabulatedFunction thirdArrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, 1,100,100);
        assertTrue(firstArrayTabulatedFunction.equals(secondArrayTabulatedFunction));
        assertFalse(firstArrayTabulatedFunction.equals(thirdArrayTabulatedFunction));
    }

    @Test
    void testHashCode() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction firstArrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        ArrayTabulatedFunction firstArrayTabulatedFunctionCopy = firstArrayTabulatedFunction;
        ArrayTabulatedFunction secondArrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        assertEquals(firstArrayTabulatedFunction.hashCode(), firstArrayTabulatedFunctionCopy.hashCode());
        assertNotEquals(firstArrayTabulatedFunction.hashCode(), secondArrayTabulatedFunction.hashCode());
    }

    @Test
    void testClone() {
        SqrFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction firstArrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
        ArrayTabulatedFunction firstArrayTabulatedFunctionClone = (ArrayTabulatedFunction) firstArrayTabulatedFunction.clone();
        assertTrue(firstArrayTabulatedFunction.equals(firstArrayTabulatedFunctionClone));
    }
    @Test
    void constructorOneEx(){
        double[] X = {1};
        double[] Y = {1};
        assertThrows(IllegalArgumentException.class, () ->{
            ArrayTabulatedFunction atf = new ArrayTabulatedFunction(X,Y);
        });
    }

    @Test
    void constructorTwoEx(){
        cos2xFunctions cos = new cos2xFunctions();
        assertThrows(IllegalArgumentException.class, () ->{
            ArrayTabulatedFunction atf = new ArrayTabulatedFunction(cos, 1, 10, 1);
        });
    }

    @Test
    void floorIndexOfXex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.floorIndexOfX(0);
        });
    }
    @Test
    void getXex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.getX(4);
        });
    }
    @Test
    void getYex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.getY(-1);
        });
    }

    @Test
    void setYex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.setY(5,5.5);
        });
    }
    @Test
    void indexOfXex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.indexOfX(-1.5);
        });
    }
    @Test
    void indexOfYex(){
        double[] X = {1,2,3,4};
        double[] Y = {1,3,5};
        ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
        assertThrows(IllegalArgumentException.class, () ->{
            ATF.getX(-2);
        });
    }
}