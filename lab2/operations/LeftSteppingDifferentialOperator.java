package operations;

import functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> (function.apply(x) - function.apply(x - step)) / step;
    }

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }
}