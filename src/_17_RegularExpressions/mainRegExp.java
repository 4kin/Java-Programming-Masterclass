package _17_RegularExpressions;

import javax.sound.midi.Soundbank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainRegExp {
    public static void main(String[] args) {
        String string = "������ ������ ��� ��� ����";
        System.out.println(string);
        String myString = string.replaceAll("���", "����");
        System.out.println(myString);

        String alphanumeric = "asdhbvcDeeere089897123";
        System.out.println(alphanumeric.replaceAll(".", "Y"));
        System.out.println(alphanumeric.replaceAll("^asdhbvc", "_______"));

        String secondString = "asdhbvcDeeere0������ 89897123oiuh9834yfsdlkfj";
        System.out.println(secondString.replaceAll("^asdhbvc", "_______"));

        System.out.println(alphanumeric.matches("(.*)(08)(.*)"));
        System.out.println(alphanumeric.matches("89"));
        System.out.println(alphanumeric.matches("asdhbvcDeeere089897123"));


        String newAlphanumeric = "asg ������ ������ ����  dhbvcDeeere0898971 23";
        System.out.println(newAlphanumeric.replaceAll("[a���]", "_������_"));

        StringBuilder htmlText = new StringBuilder("<h1> My Heading</h1>");
        htmlText.append("<h2>sub-heading</h2>");
        htmlText.append("<p>��� �������� � �����</p>");
        htmlText.append("<p>��� ������ �������� � ����� ������</p>");
        htmlText.append("<h2>��������</h2>");
        htmlText.append("<p>��� ��������</p>");

        String h2Pattern = "<[/]*h2>";
        Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE+Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset(); // ��������� �������� ������
        int count = 0;

        while (matcher.find()) {
            count++;
            System.out.println("����� " + count + ":" + matcher.start() + " �� " + matcher.end());
        }

        String h2GroupPattern = "(<h2>.*?</h2>)"; // ? - ������ �������� �� ������
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());

        groupMatcher.reset();
        while (groupMatcher.find()) {
            System.out.println("��������� - " + groupMatcher.group(1));
        }

        System.out.println("4------------");
        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while (h2TextMatcher.find()) {
            System.out.println("��������� - " + h2TextMatcher.group(2));
        }

        System.out.println("5------------");

        // "abc" "a" and ""b" and "c"
        // [�|�]�����

        System.out.println("������".replaceAll("[�|�]�����","������ ������"));
        System.out.println("������".replaceAll("[�|�]�����","������ ������"));

        // [^abc]
        String tvTest = "tstvtk";
        String tyNotVRegExp = "t[^v]";
        Pattern tNotVPattern = Pattern.compile(tyNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;

        while (tNotVMatcher.find()){

            System.out.println("���������� �" + ++count +  " - � " + tNotVMatcher.start() + " � �� " + tNotVMatcher.end());
        }

    }


}
