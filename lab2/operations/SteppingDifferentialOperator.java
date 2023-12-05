package operations;

import functions.MathFunction;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;


public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || step == NaN || step == POSITIVE_INFINITY) throw new IllegalArgumentException();
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}