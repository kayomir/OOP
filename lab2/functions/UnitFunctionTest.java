package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {
    @Test
    void apply() {
        UnitFunction a = new UnitFunction();
        assertEquals(a.apply(15), 1);
    }
}