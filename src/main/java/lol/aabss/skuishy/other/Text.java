package lol.aabss.skuishy.other;

import java.util.ArrayList;
import java.util.List;

public class Text {
    public static String[] smallCaps(String... string) {
        List<String> strings = new ArrayList<>();
        for (String s : string) {
            s = s.replaceAll("a", "\u1D00");
            s = s.replaceAll("b", "\u0299");
            s = s.replaceAll("c", "\u1D04");
            s = s.replaceAll("d", "\u1D05");
            s = s.replaceAll("e", "\u1D07");
            s = s.replaceAll("f", "\u0493");
            s = s.replaceAll("g", "\u0262");
            s = s.replaceAll("h", "\u029C");
            s = s.replaceAll("i", "\u026A");
            s = s.replaceAll("j", "\u1D0A");
            s = s.replaceAll("k", "\u1D0B");
            s = s.replaceAll("l", "\u029F");
            s = s.replaceAll("m", "\u1D0D");
            s = s.replaceAll("n", "\u0274");
            s = s.replaceAll("o", "\u1D0F");
            s = s.replaceAll("p", "\u1D18");
            s = s.replaceAll("q", "\u01EB");
            s = s.replaceAll("r", "\u0280");
            s = s.replaceAll("s", "s");
            s = s.replaceAll("t", "\u1D1B");
            s = s.replaceAll("u", "\u1D1C");
            s = s.replaceAll("v", "\u1D20");
            s = s.replaceAll("w", "\u1D21");
            s = s.replaceAll("x", "x");
            s = s.replaceAll("y", "\u028F");
            s = s.replaceAll("z", "\u1D22");
            strings.add(s);
        }
        return strings.toArray(new String[0]);
    }

    public static String[] reallySmallCaps(String... string) {
        List<String> strings = new ArrayList<>();
        for (String s : string) {
            s = s.replaceAll("a", "\u1D43").replaceAll("A", "\u1D2C");
            s = s.replaceAll("b", "\u1D47").replaceAll("B", "\u1D2E");
            s = s.replaceAll("c", "\u1D9C").replaceAll("C", "\u1D9C");
            s = s.replaceAll("d", "\u1D48").replaceAll("D", "\u1D30");
            s = s.replaceAll("e", "\u1D49").replaceAll("E", "\u1D31");
            s = s.replaceAll("f", "\u1DA2").replaceAll("F", "\u1DA0");
            s = s.replaceAll("g", "\u1D4D").replaceAll("G", "\u1D33");
            s = s.replaceAll("h", "\u02B0").replaceAll("H", "\u02B0");
            s = s.replaceAll("i", "\u2071").replaceAll("I", "\u2071");
            s = s.replaceAll("j", "\u02B2").replaceAll("J", "\u02B2");
            s = s.replaceAll("k", "\u1D4F").replaceAll("K", "\u1D4F");
            s = s.replaceAll("l", "\u02E1").replaceAll("L", "\u1D38");
            s = s.replaceAll("m", "\u1D50").replaceAll("M", "\u1D39");
            s = s.replaceAll("n", "\u207F").replaceAll("N", "\u1D3A");
            s = s.replaceAll("o", "\u1D52").replaceAll("O", "\u1D3C");
            s = s.replaceAll("p", "\u1D56").replaceAll("P", "\u1D3E");
            s = s.replaceAll("q", "\u1D56").replaceAll("Q", "\u146B");
            s = s.replaceAll("r", "\u02B3").replaceAll("R", "\u02B3");
            s = s.replaceAll("s", "\u02E2").replaceAll("S", "\u02E2");
            s = s.replaceAll("t", "\u1D57").replaceAll("T", "\u1D1B");
            s = s.replaceAll("u", "\u1D58").replaceAll("U", "\u1D1C");
            s = s.replaceAll("v", "\u1D5B").replaceAll("V", "\u1D20");
            s = s.replaceAll("w", "\u02B7").replaceAll("W", "\u1D21");
            s = s.replaceAll("x", "\u02E3").replaceAll("X", "\u02E3");
            s = s.replaceAll("y", "\u02B8").replaceAll("Y", "\u02B8");
            s = s.replaceAll("z", "\u1D5D").replaceAll("Z", "\u1D22");
            strings.add(s);
        }
        return strings.toArray(new String[0]);
    }
}
