package functions.factory;

import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {
    double[] xValue = {1, 2, 3.3, 4.4, 5, 6};
    double[] yValue = {1, 2, 3, 4, 5, 6};
    LinkedListTabulatedFunction linkedListTabulatedFunction=new LinkedListTabulatedFunction(xValue,yValue);
    @Test
    void create(){
        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        assertTrue(linkedListTabulatedFunction.getClass() == linkedListTabulatedFunctionFactory.create(xValue, yValue).getClass());
    }
}