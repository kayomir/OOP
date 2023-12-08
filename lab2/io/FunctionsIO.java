package io;
import functions.Point;
import functions.TabulatedFunction;
import java.io.*;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeInt(function.getCount());
        for (Point point : function) {
            dos.writeDouble(point.x);
            dos.writeDouble(point.y);
        }
        dos.flush();
    }
}
