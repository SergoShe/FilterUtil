package com.github.SergoShe.filter;

import com.github.SergoShe.parameter.Parameters;
import com.github.SergoShe.statistic.StatisticManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileFilterProcess {

    private final SortedData sortedData;
    private final StatisticManager statisticManager;

    public FileFilterProcess(StatisticManager statisticManager) {
        this.statisticManager = statisticManager;
        sortedData = new SortedData();
    }

    public void start(Parameters parameters) {
        doProcess(parameters);
    }

    private void doProcess(Parameters parameters) {
        parameters.getInputFiles().forEach(this::readAndCountFiles);
        writeFiles(parameters);
        statisticManager.showAllStatistic();
    }

    private void readAndCountFiles(String inputPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Type type = TypeParser.detectType(line);
                sortedData.addValue(line, type);
                statisticManager.count(line, type);
            }
        } catch (IOException e) {
            System.out.println("Ошибка! Не удалось прочитать файл " + inputPath);
            System.out.println("Файл будет пропущен. Подробности: " + e.getMessage());
        }
    }

    private void writeFiles(Parameters parameters) {
        Path outputPathWay;
        if (!sortedData.getStringList().isEmpty()) {
            outputPathWay = createOutputPathWay(parameters, Type.STRING);
            writeFile(sortedData.getStringList(), outputPathWay, parameters.isAppendMode());
        }
        if (!sortedData.getIntegerList().isEmpty()) {
            outputPathWay = createOutputPathWay(parameters, Type.INTEGER);
            writeFile(sortedData.getIntegerList(), outputPathWay, parameters.isAppendMode());
        }
        if (!sortedData.getFloatList().isEmpty()) {
            outputPathWay = createOutputPathWay(parameters, Type.FLOAT);
            writeFile(sortedData.getFloatList(), outputPathWay, parameters.isAppendMode());
        }
    }

    private Path createOutputPathWay(Parameters parameters, Type type) {
        String outputPathWay = parameters.getOutputPathWay().toString() +
                File.separator +
                parameters.getPrefix() +
                type.getType() +
                "s.txt";
        return Paths.get(outputPathWay);
    }

    private void writeFile(List<String> outputList, Path pathWay, boolean appendMode) {
        try {
            Files.createDirectories(pathWay.getParent());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathWay.toString(), appendMode))) {
                for (String line : outputList) {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка! не удалось создать и/или записать файл " + pathWay.getFileName() + "\n");
        }

    }
}
