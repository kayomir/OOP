package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void apply() {
        SqrFunction a = new SqrFunction();
        assertEquals(a.apply(2), 4);
    }
}