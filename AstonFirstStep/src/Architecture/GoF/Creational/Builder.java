package Architecture.GoF.Creational;

public abstract class Builder<T,Y> {
    public abstract void append(Y context);
    public abstract T build();
}
