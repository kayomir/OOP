package operations;

import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SteppingDifferentialOperator rightSteppingDifferentialOperator = new RightSteppingDifferentialOperator(1);
        SqrFunction sqrFunction = new SqrFunction();
        MathFunction deferentialSqrFunction = rightSteppingDifferentialOperator.derive(sqrFunction);
        assertEquals(14, deferentialSqrFunction.apply(7), 1);
        assertEquals(16, deferentialSqrFunction.apply(8), 1);
    }
}