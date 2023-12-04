package Test;

import functions.ConstantFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void apply() {
        ConstantFunction a = new ConstantFunction(15);
        assertEquals(a.apply(100), 15);
    }
}