package io;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(function);
        objectOutputStream.flush();
    }
    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
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
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function){
        try{
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeInt(function.getCount());
            for (Point point : function) {
                dos.writeDouble(point.x);
                dos.writeDouble(point.y);
            }
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValue = new double[count];
        double[] yValue = new double[count];
        NumberFormat formatter;
        formatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String[] str = reader.readLine().split(" ");
            try {
                xValue[i] = formatter.parse(str[0]).doubleValue();
                yValue[i] = formatter.parse(str[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException(e);
            }
        }
        return factory.create(xValue, yValue);
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