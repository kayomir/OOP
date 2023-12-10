package io;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;
public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input/binary function.bin"))){
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(bis, factory);
            System.out.println(function.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader list = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции ");
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction linkedList = FunctionsIO.readTabulatedFunction(list, factory);
            TabulatedDifferentialOperator TDO = new TabulatedDifferentialOperator();
            TabulatedFunction result = TDO.derive(linkedList);
            System.out.println(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
