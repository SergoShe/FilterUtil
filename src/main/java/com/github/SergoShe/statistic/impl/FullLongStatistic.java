package com.github.SergoShe.statistic.impl;

import java.math.BigInteger;

public class FullLongStatistic extends ShortStatistic {

    private Long max = Long.MIN_VALUE;
    private Long min = Long.MAX_VALUE;
    private BigInteger sum = BigInteger.ZERO;

    @Override
    public void count(String string) {
        super.count(string);
        long value = Long.parseLong(string);
        max = Math.max(value, max);
        min = Math.min(value, min);
        sum = sum.add(BigInteger.valueOf(value));
    }

    @Override
    public void showStatistic() {
        super.showStatistic();
        if (counter != 0) {
            System.out.println("Минимальное число: " + min);
            System.out.println("Максимальное число: " + max);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + sum.divide(BigInteger.valueOf(counter)));
            System.out.println();
        }
    }
}
