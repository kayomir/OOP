package operations;

import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SteppingDifferentialOperator leftSteppingDifferentialOperator = new LeftSteppingDifferentialOperator(1);
        SqrFunction sqrFunction = new SqrFunction();
        MathFunction deferentialSqrFunction = leftSteppingDifferentialOperator.derive(sqrFunction);
        assertEquals(10, deferentialSqrFunction.apply(5), 1);
        assertEquals(30, deferentialSqrFunction.apply(15), 1);
    }
}