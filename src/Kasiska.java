import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kasiska {
    // Угадываем длину ключевого слова зашифрованного текста
    public int kasiski(String s, int len) {
        Map<String, Integer> m = new HashMap<>(); // lgram + колчиество повторений

        for (int i = 0; i < s.length() - len + 1; i++) {
            String lgram = s.substring(i, i + len);
            boolean allAlphas = true;
            for (char c: lgram.toCharArray()) {
                if (!(c >= 'A' && c <= 'Z')) {
                    allAlphas = false;
                }
            }
            if (!allAlphas) {
                continue;
            }
            if (m.containsKey(lgram)) {
                continue;
            }

            int index = i;
            int count = 1;
            while ((index = s.indexOf(lgram, index + 1)) != -1) {
                count++;
            }
            if (count > 2) {
                m.put(lgram, count);
            }
        }

        // сортируем по количеству вхождений
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(m.entrySet());
        list.sort((p1,p2) -> p2.getValue() - p1.getValue());
        //System.out.println(list.toString());

        int res = -1;
        for (int i = 0; i < 5 && i < list.size(); i++) { // смотрим 5 самых частых л-грамм
            String lgram = list.get(i).getKey();
            int prevIndex = s.indexOf(lgram);
            int index = s.indexOf(lgram, prevIndex + 1);
            int a = index - prevIndex;
            for (; index != -1; prevIndex = index, index = s.indexOf(lgram, index + 1)) {
                int b = index - prevIndex;
                a = nod(a, b);
            }
            res = a;
            if (res > 2) {
                break;
            }
        }
        return res;
    }

    private int nod(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (a != 0 && b != 0) {
            a = a % b;
            if (a<b) {
                int temp = a;
                a = b;
                b = temp;
            }
        }
        return a;
    }

    public String vigenerGuessKey(String s, int kwlen) {
        Frequency frequency = new Frequency();
        StringBuilder keyword = new StringBuilder();

        // для каждой подстрочки делаем частотный анализ
        for (int i = 0; i < kwlen; i++) {
            String subStr = getSubStringEveryL(s, kwlen, i);
            keyword.append((char)(frequency.frequencyGetOffset(subStr) + 'A'));
        }
        return keyword.toString();
    }

    // дает строчку с каждым l-ым символом начиная с оффсета
    private String getSubStringEveryL(String s, int l, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < s.length(); i += l) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
