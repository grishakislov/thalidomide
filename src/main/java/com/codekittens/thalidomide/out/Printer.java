package com.codekittens.thalidomide.out;

import java.util.List;

public abstract class Printer<T> {

    public abstract void print(T input);

    public void print(List<T> input) {
        input.forEach((T value) -> print(value));
    }

}