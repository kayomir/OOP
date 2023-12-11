package io;
import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;
public class ArrayTabulatedFunctionSerialization {
    public ArrayTabulatedFunctionSerialization() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
            double[] xValue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            double[] yValue = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
            TabulatedFunction ATF = new ArrayTabulatedFunction(xValue, yValue);
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDifATF = tabulatedDifferentialOperator.derive(ATF);
            TabulatedFunction secondDifATF = tabulatedDifferentialOperator.derive(firstDifATF);
            FunctionsIO.serialize(bufferedOutputStream, ATF);
            FunctionsIO.serialize(bufferedOutputStream, firstDifATF);
            FunctionsIO.serialize(bufferedOutputStream, secondDifATF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}