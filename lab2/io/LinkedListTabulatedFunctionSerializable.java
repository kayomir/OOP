package io;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;
public class LinkedListTabulatedFunctionSerializable {
    public static void main(String[] args) {
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            double[] xValue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            double[] yValue = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
            TabulatedFunction listTabulatedFunction = new LinkedListTabulatedFunction(xValue, yValue);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDif = operator.derive(listTabulatedFunction);
            TabulatedFunction secondDif = operator.derive(firstDif);
            FunctionsIO.serialize(bos, listTabulatedFunction);
            FunctionsIO.serialize(bos, firstDif);
            FunctionsIO.serialize(bos, secondDif);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(bis));
            System.out.println(FunctionsIO.deserialize(bis));
            System.out.println(FunctionsIO.deserialize(bis));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
