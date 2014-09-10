package com.codekittens.thalidomide.out;

import com.codekittens.thalidomide.model.ServerResponse;

public abstract class AbstractPrinter<T extends ServerResponse> {

    public abstract void print(T input);

}
