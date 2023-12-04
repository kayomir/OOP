package Test;

import functions.ZeroFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    @Test
    void apply() {
        ZeroFunction a = new ZeroFunction();
        assertEquals(a.apply(15), 0);
    }
}