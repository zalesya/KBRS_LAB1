public class Caesar {
    // Шифровать
    public String encryptCesar(String text, int len) {
        if (text.isEmpty()) {
            throw new RuntimeException("Text is empty");
        }
        StringBuilder sb = new StringBuilder(text.toUpperCase());
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, encryptChar(sb.charAt(i), len));
        }
        return sb.toString();
    }

    // Расшифровать
    public String decryptCesar(String text, int len) {
        return encryptCesar(text, -len);
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