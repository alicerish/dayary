package us.spring.dayary.common.tool;

public class XSS {

    public static String xssShield(String str) {

        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\'", "&apos;");
        str = str.replaceAll(" ", "&nbsp;");
        str = str.replaceAll("\n", "<br>");

        return str;
    }
}
