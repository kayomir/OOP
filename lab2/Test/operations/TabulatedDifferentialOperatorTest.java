package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {
    double[] xValue = {1, 2, 3, 4, 5};
    double[] yValue = {1, 4, 9, 16, 25};

    @Test
    void setF() {
        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
        tabulatedDifferentialOperator.setF(linkedListTabulatedFunctionFactory);
        assertTrue(linkedListTabulatedFunctionFactory.getClass() == tabulatedDifferentialOperator.getF().getClass());

        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        assertFalse(arrayTabulatedFunctionFactory.getClass() ==  tabulatedDifferentialOperator.getF().getClass());

        tabulatedDifferentialOperator.setF(arrayTabulatedFunctionFactory);
        assertFalse(linkedListTabulatedFunctionFactory.getClass() == tabulatedDifferentialOperator.getF().getClass());
        assertTrue(arrayTabulatedFunctionFactory.getClass() == tabulatedDifferentialOperator.getF().getClass());
    }

    @Test
    void derive() {
        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);
        TabulatedFunction derive = tabulatedDifferentialOperator.derive(arrayTabulatedFunction);
        for(int i = 0; i < arrayTabulatedFunction.getCount(); i++){
            assertEquals(derive.getX(i), arrayTabulatedFunction.getX(i));
        }
        assertEquals(3, derive.getY(0));
        assertEquals(5, derive.getY(1));
        assertEquals(7, derive.getY(2));
        assertEquals(9, derive.getY(3));

        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator1 = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
        TabulatedFunction list = new LinkedListTabulatedFunction(xValue, yValue);
        derive = tabulatedDifferentialOperator1.derive(list);
        for(int i = 0; i < list.getCount(); i++){
            assertEquals(derive.getX(i), list.getX(i));
        }
        assertEquals(3, derive.getY(0));
        assertEquals(5, derive.getY(1));
        assertEquals(7, derive.getY(2));
        assertEquals(9, derive.getY(3));
    }
}