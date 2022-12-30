package _17_RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chalenge {
    public static void main(String[] args) {
        String chalenge = "Я хочу кататься на бейке.";
        System.out.println(chalenge);
        System.out.println(chalenge.matches("Я хочу кататься на бейке."));

        String regExp = "Я хочу кататься на [а-я]+.";
        System.out.println(chalenge.matches(regExp));
        String chalenge2 = "Я хочу кататься на веревке.";
        System.out.println(chalenge2.matches(regExp));

        String regExp1 = "Я хочу кататься на (бейке|веревке).";
        System.out.println(chalenge.matches(regExp1));
        System.out.println(chalenge2.matches(regExp1));

        String regExp3 = "Я хочу кататься на [а-я]+.";
        Pattern pattern = Pattern.compile(regExp3);
        Matcher matcher = pattern.matcher(chalenge);
        System.out.println(matcher.matches());

        matcher =pattern.matcher(chalenge2);
        System.out.println(matcher.matches());


        String chalenche4 = "Заменить все пробелы на подчеркивание";
        System.out.println(chalenche4.replaceAll(" ", "_"));
        System.out.println(chalenche4.replaceAll("\\s", "_"));

        String chalenge5 = "аааааабсссссссссефффффффж";
        System.out.println(chalenge5.matches("[абсефж]+"));
        System.out.println(chalenge5.matches("[а-я]+"));


    }
}
