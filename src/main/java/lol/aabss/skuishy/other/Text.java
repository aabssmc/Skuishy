package lol.aabss.skuishy.other;

public class Text {
    private static final char[] normal = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] tiny = "\u1D00\u0299\u1D04\u1D05\u1D07\u0493\u0262\u029C\u026A\u1D0A\u1D0B\u029F\u1D0D\u0274\u1D0F\u1D18\u01EB\u0280s\u1D1B\u1D1C\u1D20\u1D21x\u028F\u1D22".toCharArray();
    private static final char[] supertiny = "\u1D43\u1D47\u1D9C\u1D48\u1D49\u1DA0\u1D4D\u02B0\u1DA6\u02B2\u1D4F\u02E1\u1D50\u207F\u1D52\u1D56\u1D60\u02B3\u02E2\u1D57\u1D58\u1D5B\u02B7\u02E3\u02B8\u1DBB".toCharArray();

    public static String tinyText(String s, boolean superTiny){
        int i = 0;
        boolean b = false;
        StringBuilder string = new StringBuilder(s);
        for (char st : superTiny ? supertiny : tiny) {
            if (st == '&') {
                ++i;
                b = true;
                continue;
            }
            if (!b) {
                String find = Character.toString(normal[i]);
                String replace = Character.toString(st);
                int index = string.indexOf(find);
                while (index != -1) {
                    string.replace(index, index + find.length(), replace);
                    index = string.indexOf(find, index + replace.length());
                }
            } else {
                b = false;
            }
            ++i;
        }
        return string.toString();
    }

}
