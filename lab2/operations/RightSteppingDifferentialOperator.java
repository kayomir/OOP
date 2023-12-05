package operations;

import functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {

    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> ((function.apply(x + step) - function.apply(x)) / step);
    }

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }
}