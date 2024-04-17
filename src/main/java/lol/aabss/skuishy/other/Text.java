package lol.aabss.skuishy.other;

import java.util.ArrayList;
import java.util.List;

public class Text {

    private static final char[] smallcaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZᴀʙᴄᴅᴇғɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ".toCharArray();
    private static final char[] reallysmallcaps = "ᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾᵠᴿˢᵀᵁⱽᵂˣʸᶻᵃᵇᶜᵈᵉᶠᵍʰᶦʲᵏˡᵐⁿᵒᵖᵠʳˢᵗᵘᵛʷˣʸᶻ".toCharArray();

    public static String[] smallCaps(String... strings) {
        List<String> stringlist = new ArrayList<>();
        for (String s : strings) {
            StringBuilder smallCaps = new StringBuilder(s.length());
            for (int i=0; i<s.length(); ++i) {
                char c = s.charAt(i);
                if (c >= 'A' && c <= 'z') {
                    smallCaps.append(smallcaps[c - 'a']);
                } else {
                    smallCaps.append(c);
                }
            }
            stringlist.add(smallCaps.toString());
        }
        return stringlist.toArray(String[]::new);
    }

    public static String[] reallySmallCaps(String... strings) {
        List<String> stringlist = new ArrayList<>();
        for (String s : strings) {
            StringBuilder smallCaps = new StringBuilder(s.length());
            for (int i=0; i<s.length(); ++i) {
                char c = s.charAt(i);
                if (c >= 'A' && c <= 'z') {
                    smallCaps.append(reallysmallcaps[c - 'a']);
                } else {
                    smallCaps.append(c);
                }
            }
            stringlist.add(smallCaps.toString());
        }
        return stringlist.toArray(String[]::new);
    }
}
