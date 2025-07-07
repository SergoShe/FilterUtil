package com.github.SergoShe.statistic.impl;

public class FullStringStatistic extends ShortStatistic {

    private int maxLength = Integer.MIN_VALUE;
    private int minLength = Integer.MAX_VALUE;

    @Override
    public void update(String string) {
        super.update(string);
        int length = string.length();
        maxLength = Math.max(length, maxLength);
        minLength = Math.min(length, minLength);
    }

    @Override
    public void showStatistic() {
        super.showStatistic();
        if (counter != 0) {
            System.out.println("Минимальная длина строки: " + minLength);
            System.out.println("Максимальная длина строки: " + maxLength);
            System.out.println();
        }
    }
}
