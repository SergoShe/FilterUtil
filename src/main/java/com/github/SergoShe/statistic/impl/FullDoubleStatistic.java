package com.github.SergoShe.statistic.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FullDoubleStatistic extends ShortStatistic {

    private Double max = Double.MIN_VALUE;
    private Double min = Double.MAX_VALUE;
    private BigDecimal sum = BigDecimal.ZERO;

    @Override
    public void count(String string) {
        super.count(string);
        double value = Double.parseDouble(string);
        max = Math.max(value, max);
        min = Math.min(value, min);
        sum = sum.add(BigDecimal.valueOf(value));
    }

    @Override
    public void showStatistic() {
        super.showStatistic();
        if (counter != 0) {
            sum = sum.setScale(8,RoundingMode.HALF_UP);
            BigDecimal average = sum.divide(BigDecimal.valueOf(counter), RoundingMode.HALF_UP);
            System.out.println("Минимальное число: " + min);
            System.out.println("Максимальное число: " + max);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + average);
            System.out.println();
        }
    }
}
