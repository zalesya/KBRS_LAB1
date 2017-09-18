public class Main {
    private static final int POSITION_CESAR_L = 3;
    private static final String KEYWORD_VIGENER = "frequency";

    public static void main(String[] args) {
        Caesar caesar = new Caesar();
        ReadWriteFiles readWriteFiles = new ReadWriteFiles();
        Vigener vigener = new Vigener();
        TestTextLength testTextLength = new TestTextLength();

        String text = readWriteFiles.readFile("test.txt");
        String cesarEncodingText = caesar.encryptCesar(text, POSITION_CESAR_L);
        readWriteFiles.writeFile("CesarEncode.txt", cesarEncodingText);

        String cesarDecodingText = caesar.decryptCesar(cesarEncodingText, POSITION_CESAR_L);
        readWriteFiles.writeFile("CesarDecode.txt", cesarDecodingText);

        String vigenerEncodingText = vigener.encryptVigener(text, KEYWORD_VIGENER);
        readWriteFiles.writeFile("VigenerEncode.txt", vigenerEncodingText);

        String vigenerDecodingText = vigener.decryptVigener(vigenerEncodingText, KEYWORD_VIGENER);
        readWriteFiles.writeFile("VigenerDecode.txt", vigenerDecodingText);

        testTextLength.testTextLengthByKasiskaTask3(30, 0.4, 12);
        testTextLength.testKeywordLengthByKasiskaTask4(30, 0.4, 1000);
    }
}
