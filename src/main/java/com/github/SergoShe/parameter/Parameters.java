package com.github.SergoShe.parameter;


import lombok.Getter;
import lombok.Setter;
import com.github.SergoShe.statistic.StatisticType;

import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public class Parameters {
    private final List<String> inputFiles;
    private final Path outputPathWay;
    private final String prefix;
    private final boolean appendMode;
    private final StatisticType statisticType;

    public Parameters(List<String> inputPaths, Path outputPathWay, String prefix, boolean addMode, StatisticType statisticType) {
        this.inputFiles = inputPaths;
        this.outputPathWay = outputPathWay;
        this.prefix = prefix;
        this.appendMode = addMode;
        this.statisticType = statisticType;
    }
}
