package _18_DebuggingAndUnitTesting;

public class StringUtilites {
    private StringBuilder sBulder = new StringBuilder();
    private int charsAdded = 0;

    public void addChar(StringBuilder sBulder, char c) {
        sBulder.append(c);
        charsAdded++;
    }

    public String upperAndPrefix(String str) {
        return "Prefix__" + str.toUpperCase();
    }

    public String addSufix(String str) {
        return str + "__Suffix";
    }
}
