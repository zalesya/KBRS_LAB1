import java.util.Random;

public class TestTextLength {
    public void testTextLengthByKasiskaTask3(int n, double eps, int keywordLength) {
        StringBuilder str1 = new StringBuilder();
        Kasiska kasiska = new Kasiska();
        Vigener vigener = new Vigener();
        ReadWriteFiles readWriteFiles = new ReadWriteFiles();
        int[] textLengths = {500, 750, 1000, 1250, 1500};
        String bigText = readWriteFiles.readFile("test.txt").toUpperCase();
        String bigKeyword = "OnemorningwhenGregorbootstransformedinhisbedintoahorribleverminHeayonhisarmourlike".toUpperCase();

        for (int textLength: textLengths) {
            double successLength = 0, successKeywordEps = 0, successKeywordWhole = 0;
            for (int i = 1; i <= n; i++) {
                int textPosition = new Random().nextInt(bigText.length() - textLength);    // берем рандомный текст
                int keywordPosition = new Random().nextInt(bigKeyword.length() - keywordLength); // берем рандомный ключ
                String text = bigText.substring(textPosition, textPosition + textLength);
                String keyword = bigKeyword.substring(keywordPosition, keywordPosition + keywordLength);

                String encoded = vigener.encryptVigener(text, keyword);
                System.out.println(/*"text = " + text +*/ "\nkeyword = " + keyword /*+ "\nenc = " + encoded*/);
                int keywordGuessedLength = kasiska.kasiski(encoded, 3); //keyword guessed length

                if (keywordGuessedLength == keywordLength) {
                    successLength++;
                    String guessKeyword = kasiska.vigenerGuessKey(encoded, keywordGuessedLength);

                    double p = similarity(keyword, guessKeyword);
                    if (p >= eps) {
                        successKeywordEps++;
                    }
                    if (p == 1.0) {
                        successKeywordWhole++;
                    }
                    System.out.println("RIGHTLength keyword: " + keyword + " guessKeyword: " + guessKeyword);
                } else {
                    System.out.println("wrongLen keyword: " + keyword + " keywordGuessedLength " + keywordGuessedLength);
                }
                System.out.println("\n___________________________________________________");
                System.out.println();
            }
            str1.append("textLength = ").append(textLength).append(" successLength = ").append(successLength / n).append(" successKeywordEps = ").append(successKeywordEps / n).append(" successKeywordWhole = ").append(successKeywordWhole / n).append("\n");
        }
        readWriteFiles.writeFile("AttackAnalysisTextLengthTests.txt", str1.toString());
    }

    // фиксированный textLength
    public void testKeywordLengthByKasiskaTask4(int n, double eps, int textLength) {
        StringBuilder str1 = new StringBuilder();
        ReadWriteFiles readWriteFiles = new ReadWriteFiles();
        Vigener vigener = new Vigener();
        Kasiska kasiska = new Kasiska();
        int[] keywordLengths = {3, 5, 10, 15, 20};
        String bigText = readWriteFiles.readFile("test.txt");
        String bigKeyword = "OnemorningwhenGregorbootstransformedinhisbedintoahorribleverminHeayonhisarmourlike".toUpperCase();
        for (int keywordLength: keywordLengths) {
            double successLength = 0, successKeywordEps = 0, successKeywordWhole = 0;
            for (int i = 1; i <= n; i++) {
                int textPosition = new Random().nextInt(bigText.length() - textLength); // берем рандомный текст и ключ
                int keywordPosition = new Random().nextInt(bigKeyword.length() - keywordLength);
                String text = bigText.substring(textPosition, textPosition+textLength);
                String keyword = bigKeyword.substring(keywordPosition, keywordPosition + keywordLength);

                String encoded = vigener.encryptVigener(text, keyword);
                //System.out.println("text = " + text + "\nkwd = " + keyword /*+ "\nenc = " + encoded*/);
                int keywordGuessedLength = kasiska.kasiski(encoded, 3); //keyword guessed length

                if (keywordGuessedLength == keywordLength) {
                    successLength++;
                    String guessKey = kasiska.vigenerGuessKey(encoded, keywordGuessedLength);

                    double p = similarity(keyword, guessKey);
                    if (p >= eps) {
                        successKeywordEps++;
                    }
                    if (p == 1.0) {
                        successKeywordWhole++;
                    }
                    System.out.println("RIGHTLength keyword: " + keyword + " guessKey: " + guessKey);
                } else {
                    System.out.println("wrongLength keyword: " + keyword + " keywordGuessedLength " + keywordGuessedLength);
                }
                System.out.println("\n___________________________________________________");
                System.out.println();
            }
            str1.append("keywordLength = ").append(keywordLength).append(" successLength = ").append(successLength / n).append(" successKeywordEps = ").append(successKeywordEps / n).append(" successKeywordWhole = ").append(successKeywordWhole / n).append("\n");
        }
        readWriteFiles.writeFile("AttackAnalysisKeyLengthTests.txt", str1.toString());
    }

    private double similarity(String a, String b) { // % одинаковых символов
        int n = a.length();
        int similar = 0;
        for (int i = 0; i < n; i++) {
            similar += (a.charAt(i) == b.charAt(i) ? 1 : 0);
        }
        return (double)similar / n;
    }
}