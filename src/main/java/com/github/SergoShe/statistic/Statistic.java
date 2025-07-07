package com.github.SergoShe.statistic;

public abstract class Statistic {
    protected int counter = 0;

    public abstract void update(String string);

    public abstract void showStatistic();
}
