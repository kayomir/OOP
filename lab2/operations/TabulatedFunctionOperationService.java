package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    TabulatedFunctionFactory factory;

    private interface BiOperation{
        double apply(double u, double v);
    }

    public TabulatedFunction add(TabulatedFunction firstTab, TabulatedFunction secTab){
        BiOperation operation = (u,v) -> u + v;
        return doOperation(firstTab,secTab,operation);
    }

    public TabulatedFunction sub(TabulatedFunction firstTab, TabulatedFunction secTab){
        BiOperation operation = (u,v) -> u - v;
        return doOperation(firstTab,secTab,operation);
    }

    public TabulatedFunction multiply(TabulatedFunction firstTab, TabulatedFunction secTab){
        BiOperation operation = (u,v) -> u * v;
        return doOperation(firstTab,secTab,operation);
    }

    public TabulatedFunction divide(TabulatedFunction firstTab, TabulatedFunction secTab){
        BiOperation operation = (u,v) -> u / v;
        return doOperation(firstTab,secTab,operation);
    }


    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){
        if (a.getCount() != b.getCount()) throw new InconsistentFunctionsException("Функции несопоставимы");
        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        double[] xValues = new double[pointsA.length];
        double[] yValues = new double[pointsA.length];
        for (int i = 0; i < a.getCount(); ++i){
            if (pointsA[i].x != pointsB[i].x) throw new InconsistentFunctionsException("Функции несопоставимы");
            else {
                xValues[i] = pointsA[i].x;
                yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
            }
        }
        return factory.create(xValues,yValues);
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point point : tabulatedFunction){
            points[i] = point;
            ++i;
        }
        return points;
    }
}