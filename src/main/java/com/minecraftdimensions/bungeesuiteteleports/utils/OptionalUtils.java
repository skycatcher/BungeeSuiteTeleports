package com.minecraftdimensions.bungeesuiteteleports.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalUtils<T> {
    private Supplier<T> object;

    public OptionalUtils(Supplier<T> object) {
        this.object = object;
    }

    public OptionalUtils() {
    }

    public Optional<T> getOptional() {
        T t = object.get();
        if (t != null) {
            return Optional.of(t);
        }
        return Optional.empty();
    }

    public boolean isPresent() {
        return object.get() != null;
    }

    public void ifPresent(Consumer<T> consumer) {
        if (isPresent()) {
            consumer.accept(object.get());
        }
    }

    public <U> OptionalUtils<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isPresent()) {
            return new OptionalUtils<>(() -> mapper.apply(object.get()));
        } else {
            return new OptionalUtils<>();
        }
    }

    public T orElseGet(Supplier<T> supplier) {
        if (object.get() == null) {
            this.object = supplier;
        }
        return object.get();
    }
}

