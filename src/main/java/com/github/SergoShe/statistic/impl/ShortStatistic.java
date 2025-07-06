package com.github.SergoShe.statistic.impl;

import com.github.SergoShe.statistic.Statistic;

public class ShortStatistic extends Statistic {
    @Override
    public void count(String string) {
        counter++;
    }

    @Override
    public void showStatistic() {
        System.out.println("Кол-во строк: " + counter);
        System.out.println();
    }
}
