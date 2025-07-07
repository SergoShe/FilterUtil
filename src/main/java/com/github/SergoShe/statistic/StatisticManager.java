package com.github.SergoShe.statistic;

import com.github.SergoShe.filter.Type;
import com.github.SergoShe.statistic.impl.FullDoubleStatistic;
import com.github.SergoShe.statistic.impl.FullLongStatistic;
import com.github.SergoShe.statistic.impl.FullStringStatistic;
import com.github.SergoShe.statistic.impl.ShortStatistic;

public class StatisticManager {

    private Statistic stringStatistic;

    private Statistic intStatistic;

    private Statistic floatStatistic;

    public StatisticManager(StatisticType statisticType) {
        build(statisticType);
    }


    public void update(String string, Type type) {
        switch (type) {
            case INTEGER -> intStatistic.update(string);
            case FLOAT -> floatStatistic.update(string);
            default -> {
                if (!string.isEmpty()) {
                    stringStatistic.update(string);
                }
            }
        }
    }

    public void showAllStatistic() {
        System.out.println("\nСТАТИСТИКА:\n");
        System.out.println("СТРОКИ:\n");
        stringStatistic.showStatistic();
        System.out.println("ЦЕЛЫЕ ЧИСЛА:\n");
        intStatistic.showStatistic();
        System.out.println("ВЕЩЕСТВЕННЫЕ ЧИСЛА:\n");
        floatStatistic.showStatistic();
    }

    private void build(StatisticType statisticType) {
        stringStatistic = statisticType == StatisticType.SHORT ? new ShortStatistic() : new FullStringStatistic();
        intStatistic = statisticType == StatisticType.SHORT ? new ShortStatistic() : new FullLongStatistic();
        floatStatistic = statisticType == StatisticType.SHORT ? new ShortStatistic() : new FullDoubleStatistic();
    }
}
