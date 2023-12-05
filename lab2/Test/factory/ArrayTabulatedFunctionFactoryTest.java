package functions.factory;

import functions.ArrayTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionFactoryTest {
    double[] X = {1,2,3,4,5,6,7,8,9,10};
    double[] Y = {1,4,9,16,25,36,49,64,81,100};
    ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
    @Test
    void create(){
        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        assertTrue( arrayTabulatedFunctionFactory.create(X, Y).getClass() == ATF.getClass());
    }
}