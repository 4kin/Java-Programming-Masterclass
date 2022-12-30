package _18_DebuggingAndUnitTesting;

public class mainDebug {
    public static void main(String[] args) {
        StringUtilites utils = new StringUtilites();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) {
            utils.addChar(sb, 'a');
        }
        System.out.println(sb);

        String str = "asdwerwer";
        String result = utils.upperAndPrefix(utils.addSufix(str));

    }
}
