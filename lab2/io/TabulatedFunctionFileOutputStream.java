package io;
import functions.TabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedOutputStream arrayFile = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
             BufferedOutputStream listFile = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))) {
            double[] xValue = {1,2,3,4,5,6,7,8,9,10};
            double[] yValue = {1,4,9,16,25,36,49,64,81,100};
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValue, yValue);
            TabulatedFunction listFunction = new LinkedListTabulatedFunction(xValue, yValue);

            FunctionsIO.writeTabulatedFunction(arrayFile, arrayFunction);
            FunctionsIO.writeTabulatedFunction(listFile, listFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
