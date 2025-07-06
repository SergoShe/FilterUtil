package com.github.SergoShe;

import com.github.SergoShe.filter.FileFilterProcess;
import com.github.SergoShe.parameter.ParameterBuilder;
import com.github.SergoShe.parameter.Parameters;
import com.github.SergoShe.statistic.StatisticManager;

public class FilterUtilApp {
    public static void main(String[] args) {
        if (args.length != 0 && args[0].equals("-h")) {
            printHelp();
            return;
        }
        try {
            Parameters parameters = ParameterBuilder.build(args);
            StatisticManager manager = new StatisticManager(parameters.getStatisticType());
            FileFilterProcess fileFilterProcess = new FileFilterProcess(manager);
            fileFilterProcess.start(parameters);
        } catch (IllegalArgumentException | SecurityException e) {
            System.err.println(e.getMessage());
            System.err.println("\nОпция -h для вывода справки");
        }
    }

    private static void printHelp() {
        System.out.println("""
                
                УТИЛИТА ФИЛЬТРАЦИИ СОДЕРЖИМОГО ФАЙЛОВ
                
                ***СПРАВКА***
                
                Использование утилиты:
                java -jar FilterApp.jar [опции] [входные_файлы]
                
                Входные файлы:
                Укажите как минимум один текстовый файл, содержащий строки с данными (целые, вещественные числа, строки)
                Пути к файлам могут указываться как до, так и после опций.
                
                Опции:
                    -h              - вывод справки
                
                    -o <путь>       - задает путь для сохранения результатов.
                    По умолчанию используется текущая директория.
                    Недопустимые символы пути: <>:\"|?*
                
                    -p <префикс>    - задает префикс имен выходных файлов.
                    Недопустимые символы префикса: /<>:\"|?*\\
                
                    -a              - включает режим добавления в существующие файлы.
                    По умолчанию файлы перезаписываются.
                
                    -s              - сбор краткой статистики.
                    Выводит количество элементов каждого типа данных
                
                    -f              - сбор полной статистики.
                    Выводит количество элементов каждого типа данных,а также:
                    Для чисел - min, max, сумма, среднее (округление до 8 знаков после запятой)
                    Для строк - длина самой короткой и самой длинной строки
                
                    При указании одновременно -s и -f, приоритет у -f
                
                    !!!ВАЖНО!!!
                    Программа не будет запущена, если не будет указан вид сбора статистики
                
                    Пример запуска:
                    java -jar FilterApp.jar in1.txt in2.txt -f -o /some/path -p result- -a
                """);
    }


}