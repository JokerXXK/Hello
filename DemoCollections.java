import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.In;

public class DemoCollections {
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }
    public static List<String> getWords(String inputFilename){
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        while (! in.isEmpty()) {
            String word = cleanString(in.readString());
            if (!word.equals("")) {
                words.add(word);
            }           
        }
        return words;
    }
    public static int countUniqueWords(List<String> words){
        Set<String> wordSet = new HashSet<>();
        for (String w : words) {
            wordSet.add(w);
        }
        return wordSet.size();
    }
    public static Map<String, Integer> collectWordCount(List<String> words){
        Map<String, Integer> counts = new HashMap<>(); 
        for (String w : words) {
            if (counts.containsKey(w)) {
                counts.put(w, counts.get(w) + 1);
            } else {
                counts.put(w, 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> w = getWords("tale.txt");
        Set<String> wordSet = new HashSet<>(w);
        System.out.println("Total words: " + wordSet.size());
        System.out.println(countUniqueWords(w));
    }
    
}
