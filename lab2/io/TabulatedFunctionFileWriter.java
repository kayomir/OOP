package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try (
                FileWriter arrayFileWriter = new FileWriter("output/array function.txt");
                FileWriter listFileWriter = new FileWriter("output/linked list function.txt")
        ){
            BufferedWriter arrayBufferedWriter = new BufferedWriter(arrayFileWriter);
            BufferedWriter listBufferedWriter = new BufferedWriter(listFileWriter);
            double[] X = {1,2,3,4,5,6,7,8,9,10};
            double[] Y = {1,4,9,16,25,36,49,64,81,100};
            ArrayTabulatedFunction ATF = new ArrayTabulatedFunction(X,Y);
            LinkedListTabulatedFunction LTF = new LinkedListTabulatedFunction(X,Y);
            FunctionsIO.writeTabulatedFunction(arrayBufferedWriter,ATF);
            FunctionsIO.writeTabulatedFunction(listBufferedWriter,LTF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}