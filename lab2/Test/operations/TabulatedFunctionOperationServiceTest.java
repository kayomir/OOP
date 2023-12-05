package operations;

import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    double[] X = {1,2,3,4,5,6,7,8,9,10};
    double[] Y = {1,4,9,16,25,36,49,64,81,100};
    ArrayTabulatedFunction firstATF= new ArrayTabulatedFunction(X,Y);
    SqrFunction sqrFunction = new SqrFunction();
    ArrayTabulatedFunction secondATF = new ArrayTabulatedFunction(sqrFunction, 1,10,10);
    LinkedListTabulatedFunction firstLTF = new LinkedListTabulatedFunction(X,Y);
    ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(arrayFactory);
    LinkedListTabulatedFunctionFactory linkFactory = new LinkedListTabulatedFunctionFactory();
    TabulatedFunctionOperationService linkService = new TabulatedFunctionOperationService(linkFactory);



    @Test
    void asPoints() {
        Point[] points = TabulatedFunctionOperationService.asPoints(firstATF);
        for (int i = 0; i < points.length; ++i){
            assertEquals(X[i],points[i].x);
            assertEquals(Y[i],points[i].y);
        }
        Point[] points1 = TabulatedFunctionOperationService.asPoints(secondATF);
        for (int i = 0; i < points1.length; ++i){
            assertEquals(secondATF.getX(i),points1[i].x);
            assertEquals(secondATF.getY(i),points1[i].y);
        }
        Point[] points2 = TabulatedFunctionOperationService.asPoints(firstLTF);
        for (int i = 0; i < points2.length; ++i){
            assertEquals(firstLTF.getX(i),points1[i].x);
            assertEquals(firstLTF.getY(i),points1[i].y);
        }
    }

    @Test
    void add() {
        ArrayTabulatedFunction newATF = (ArrayTabulatedFunction) service.add(firstATF,secondATF);
        for (int i = 0; i < newATF.getCount(); i++){
            assertEquals(firstATF.getY(i)+secondATF.getY(i), newATF.getY(i));
        }

        LinkedListTabulatedFunction newLTF = (LinkedListTabulatedFunction) linkService.add(firstATF,secondATF);
        for (int i = 0; i < newLTF.getCount(); i++){
            assertEquals(firstATF.getY(i)+secondATF.getY(i), newATF.getY(i));
        }

        ArrayTabulatedFunction XTF = (ArrayTabulatedFunction) service.add(firstATF,firstLTF);
        for (int i = 0; i < XTF.getCount(); i++){
            assertEquals(firstATF.getY(i)+firstLTF.getY(i), XTF.getY(i));
        }
    }

    @Test
    void sub() {
        ArrayTabulatedFunction newATF = (ArrayTabulatedFunction) service.sub(firstATF,secondATF);
        for (int i = 0; i < newATF.getCount(); i++){
            assertEquals(firstATF.getY(i)-secondATF.getY(i), newATF.getY(i));
        }

        LinkedListTabulatedFunction newLTF = (LinkedListTabulatedFunction) linkService.sub(firstATF,secondATF);
        for (int i = 0; i < newLTF.getCount(); i++){
            assertEquals(firstATF.getY(i)-secondATF.getY(i), newATF.getY(i));
        }

        ArrayTabulatedFunction XTF = (ArrayTabulatedFunction) service.sub(firstATF,firstLTF);
        for (int i = 0; i < XTF.getCount(); i++){
            assertEquals(firstATF.getY(i)-firstLTF.getY(i), XTF.getY(i));
        }
    }

    @Test
    void multiply() {
        ArrayTabulatedFunction newATF = (ArrayTabulatedFunction) service.multiply(firstATF,secondATF);
        for (int i = 0; i < newATF.getCount(); i++){
            assertEquals(firstATF.getY(i)*secondATF.getY(i), newATF.getY(i));
        }
        LinkedListTabulatedFunction newLTF = (LinkedListTabulatedFunction) linkService.multiply(firstATF,secondATF);
        for (int i = 0; i < newLTF.getCount(); i++){
            assertEquals(firstATF.getY(i)*secondATF.getY(i), newATF.getY(i));
        }

        ArrayTabulatedFunction XTF = (ArrayTabulatedFunction) service.multiply(firstATF,firstLTF);
        for (int i = 0; i < XTF.getCount(); i++){
            assertEquals(firstATF.getY(i)*firstLTF.getY(i), XTF.getY(i));
        }
    }

    @Test
    void divide() {
        ArrayTabulatedFunction newATF = (ArrayTabulatedFunction) service.divide(firstATF,secondATF);
        for (int i = 0; i < newATF.getCount(); i++){
            assertEquals(firstATF.getY(i)/secondATF.getY(i), newATF.getY(i));
        }
        LinkedListTabulatedFunction newLTF = (LinkedListTabulatedFunction) linkService.divide(firstATF,secondATF);
        for (int i = 0; i < newLTF.getCount(); i++){
            assertEquals(firstATF.getY(i)/secondATF.getY(i), newATF.getY(i));
        }

        ArrayTabulatedFunction XTF = (ArrayTabulatedFunction) service.divide(firstATF,firstLTF);
        for (int i = 0; i < XTF.getCount(); i++){
            assertEquals(firstATF.getY(i)/firstLTF.getY(i), XTF.getY(i));
        }
    }

    @Test
    void getFactory() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunctionFactory factory = service.getFactory();
        assertNotNull(factory);
    }

}