package io;
import functions.Point;
import functions.TabulatedFunction;
import java.io.*;
import functions.factory.TabulatedFunctionFactory;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        try {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(function.getCount());
            for (Point point : function) {
                printWriter.printf("%f %f\n",point.x,point.y);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        try {
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeInt(function.getCount());
            for (Point point : function) {
                dos.writeDouble(point.x);
                dos.writeDouble(point.y);
            }
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dis = new DataInputStream(inputStream);
        int length = dis.readInt();
        double[] X = new double[length];
        double[] Y = new double[length];
        for (int i = 0 ; i < length; i++){
            X[i] = dis.readDouble();
            Y[i] = dis.readDouble();
        }
        return factory.create(X, Y);
    }
}
