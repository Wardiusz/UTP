package zad3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    @SafeVarargs
    public XList(T... elements) {
        Collections.addAll(this, elements);
    }

    public XList(Collection<T> collection) {
        super(collection);
    }

    @SafeVarargs
    public static <U> XList<U> of(U... elements) {
        return new XList<>(elements);
    }

    public static <U> XList<U> of(Collection<U> collection) {
        return new XList<>(collection);
    }

    public static XList<String> tokensOf(String string, String regex) {
        return XList.of(string.split(regex));
    }

    public static XList<String> tokensOf(String string) {
        return XList.tokensOf(string, "\\s");
    }

    public static XList<String> charsOf(String string) {
        return XList.tokensOf(string, "");
    }

    @Override
    public XList<T> clone() {
        return new XList<>(this);
    }

    public XList<T> union(Collection<T> collection) {
        XList<T> copy = this.clone();
        copy.addAll(collection);
        return copy;
    }

    @SafeVarargs
    public final XList<T> union(T... elements) {
        return this.union(XList.of(elements));
    }

    public XList<T> diff(Collection<T> collection) {
        return this.stream()
                .filter(element -> !collection.contains(element))
                .collect(Collectors.toCollection(XList::new));
    }

    public XList<T> unique() {
        return new XList<>(new LinkedHashSet<>(this));
    }

    public XList<XList<String>> combine() {
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }

    public <U> XList<U> collect(Function<T, U> function) {
        return this.stream()
                .map(function)
                .collect(Collectors.toCollection(XList::new));
    }

    public String join(String delimiter) {
        return this.stream()
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }

    public String join() {
        return this.join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> function) {
        for (int i = 0; i < this.size(); i++) {
            function.accept(this.get(i), i);
        }
    }
}