package functions;

public class ConstantFunction implements MathFunction{
    private final double c;
    public ConstantFunction(double x) {
        this.c = x;
    }
    public double apply(double x) {
        return c;
    }
}