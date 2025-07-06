package com.github.SergoShe.parameter;

import com.github.SergoShe.statistic.StatisticType;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParameterBuilder {

    private static List<String> inputFiles;
    private static Path outputPath;
    private static String prefix;
    private static boolean appendMode;
    private static StatisticType statisticType;

    private ParameterBuilder() {
    }

    public static Parameters build(final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Критическая ошибка!\nПараметры отсутствуют");
        }

        inputFiles = new ArrayList<>();
        outputPath = Paths.get("").toAbsolutePath().normalize();
        prefix = "";
        appendMode = false;
        statisticType = StatisticType.NOTHING;

        parse(args);

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("Критическая ошибка!\nНет входных данных");
        }
        if (statisticType == StatisticType.NOTHING) {
            throw new IllegalArgumentException("Критическая ошибка!\nНе указана форма сбора статистики");
        }
        isValidOutputPathWay(outputPath.toString());

        return new Parameters(inputFiles, outputPath, prefix, appendMode, statisticType);
    }

    private static void parse(String[] args) {
        int index = 0;
        while (index < args.length) {
            String arg = args[index];
            switch (arg) {
                case "-o": {
                    if (++index < args.length && isValidOutputPathWay(args[index])) {
                        outputPath = Paths.get(args[index]).toAbsolutePath().normalize();
                    }
                    break;
                }
                case "-p": {
                    if (++index < args.length && isValidPrefix(args[index])) {
                        prefix = args[index];
                    }
                    break;
                }
                case "-a": {
                    appendMode = true;
                    break;
                }
                case "-s": {
                    if (statisticType != StatisticType.FULL) {
                        statisticType = StatisticType.SHORT;
                    }
                    break;
                }
                case "-f": {
                    statisticType = StatisticType.FULL;
                    break;
                }
                default: {
                    if (arg.startsWith("-")) {
                        System.out.println("Ошибка! Неизвестная опция: " + arg + "\n");
                    } else {
                        if (isValidInputPathFiles(arg)) {
                            inputFiles.add(arg);
                        }
                    }
                    break;
                }
            }
            index++;
        }
    }

    private static boolean isValidInputPathFiles(String arg) {
        Path path;
        try {
            path = Paths.get(arg).toAbsolutePath();
        } catch (InvalidPathException e) {
            System.out.println("Ошибка! Путь входного файла содержит недопустимый символ\nФайл " + arg + " не будет обработан\n");
            return false;
        }
        if (!Files.exists(path)) {
            System.out.println("Ошибка! Файл не существует. Проверьте, корректно ли введен путь\nПуть входного файла: " + arg + "\n");
            return false;
        }
        return true;
    }

    private static boolean isValidOutputPathWay(String arg) {
        Path path;
        try {
            path = Paths.get(arg).toAbsolutePath();
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Критическая ошибка!\nПуть для результатов содержит недопустимый символ");
        }

        if (Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Критическая ошибка!\nУказанный путь является файлом, а не директорией. Проверьте, корректно ли введен путь");
        }

        while (path != null && !Files.exists(path)) {
            path = path.getParent();
        }

        if (path == null) {
            throw new InvalidPathException(arg, "Критическая ошибка!\nСоздание директории невозможно. Поддиректории указанного пути не найдены\nУказанный путь");
        }
        if (!Files.isWritable(path)) {
            throw new SecurityException("Критическая ошибка!\nДоступ к директории " + path + " запрещен! Проверьте права доступа");
        }
        return true;
    }

    private static boolean isValidPrefix(String prefix) {
        String invalidChars = "/<>:\"|?*\\";
        for (char c : invalidChars.toCharArray()) {
            if (prefix.contains(String.valueOf(c))) {
                throw new IllegalArgumentException("Критическая ошибка!\nПрефикс содержит недопустимый символ");
            }
        }
        return true;
    }
}
