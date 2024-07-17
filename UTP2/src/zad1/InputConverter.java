package zad1;

import java.util.function.Function;

class InputConverter<T> {
    private final T data;

    public InputConverter(T data) {
        this.data = data;
    }

    @SafeVarargs
    public final <R> R convertBy(Function... functions) {
        R result = null;

        for (Function function : functions) {
            result = (R) function.apply((result == null) ? data : result);
        }

        return result;
    }
}