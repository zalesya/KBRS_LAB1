public class Vigener {
    public String encryptVigener(String str, String word) {
        StringBuilder sb = new StringBuilder(str.toUpperCase());
        for (int i = 0, j = 0; i < sb.length(); i++, j = (j + 1) % word.length()) {
            sb.setCharAt(i, encryptChar(sb.charAt(i), word.charAt(j) - 'A'));
        }
        return sb.toString();
    }

    public String decryptVigener(String s, String word) {
        StringBuilder sb = new StringBuilder(s.toUpperCase());
        for (int i = 0, j = 0; i < sb.length(); i++, j = (j + 1) % word.length()) {
            sb.setCharAt(i, encryptChar(sb.charAt(i), -(word.charAt(j) - 'A')));
        }
        return sb.toString();
    }

    private char encryptChar(char c, int length) {
        char res = c;
        length %= 26;

        if (c >= 'A' && c <= 'Z') {
            res = (char) ((res - 'A' + length + 26) % 26 + 'A');
        }
        return res;
    }
}
