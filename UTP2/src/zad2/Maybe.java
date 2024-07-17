package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (value != null) {
            return Maybe.of(mapper.apply(value));
        } else {
            return Maybe.empty();
        }
    }

    public T get() {
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public T orElse(T defVal) {
        return value != null ? value : defVal;
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        if (value != null && predicate.test(value)) {
            return this;
        } else {
            return Maybe.empty();
        }
    }

    public static <T> Maybe<T> empty() {
        return new Maybe<>(null);
    }

    @Override
    public String toString() {
        return value != null ? "Maybe has value " + value : "Maybe is empty";
    }
}
