package functions;

public class IdentityFunction implements MathFunction, Cloneable{
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction - класс, реализующий интерфейс MathFunction, объекты которого должны выполнять тождественное преобразование, т.е. для каждого x метод apply() должен возвращать этот же x ";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IdentityFunction;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object clone() {
        return new IdentityFunction();
    }
}