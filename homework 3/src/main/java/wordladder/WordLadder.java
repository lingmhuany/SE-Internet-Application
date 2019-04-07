package wordladder;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class WordLadder {
    private String word1;
    private String word2;
    private boolean existed = true;
    private String message;
    private String ladder;

    public HashSet<String> createDictionary(String fileName) throws Exception {
        BufferedReader br;
        FileReader fr;
        HashSet<String> dictionary = new HashSet<String>();
        try {
            fr = new FileReader(fileName); // open file
            // create dictionary
            br = new BufferedReader(fr);
            String word;
            while ((word = br.readLine()) != null) {
                dictionary.add(word);
            }
            br.close();
            System.out.println();
            return dictionary;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean validateWords(String word1, String word2, HashSet<String> dictionary) {
        if (word1.length() != word2.length()) {
            this.existed = false;
            this.message = "The two words must be the same length.";
            return false;
        } else if (!dictionary.contains(word1) || !dictionary.contains(word2)) {
            this.existed = false;
            this.message = "The two words must be found in the dictionary.";
            return false;
        } else if (word1.equals(word2)) {
            this.existed = false;
            this.message = "The two words must be differnt.";
            return false;
        } else {
            return true;
        }
    }

    public Stack<String> findLadder(String word1, String word2, HashSet<String> dictionary) {
        Queue<Stack<String>> q = new LinkedList<Stack<String>>();
        Stack<String> s = new Stack<String>();
        HashSet<String> wordsUsed = new HashSet<String>();
        Stack<String> solution = new Stack<String>();
        String w;

        s.push(word1);
        q.offer(s);

        wordsUsed.add(word1);

        int flag = 0; // the flag of stopping the loop when finding a ladder
        while (!q.isEmpty()) {
            if (flag == 1) {
                break;
            }
            // retrieve the first stack, and traverse the neibours of the top word of the stack
            s = q.peek();
            q.poll();
            w = s.peek();
            for (int i = 0; i < w.length(); i++) {
                if (flag == 1) {
                    break;
                }
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (flag == 1) {
                        break;
                    }
                    String _w = w.substring(0, i) + letter + w.substring(i + 1);
                    if (dictionary.contains(_w) && !wordsUsed.contains(_w)) {
                        if (_w.equals(word2)) { // if this word is word2, the stack is a ladder
                            s.push(_w);
                            solution = s;
                            flag = 1;
                        } else { // if this word is not word2, mark it used and put it in a copied stack, and then put the stack in the queue
                            wordsUsed.add(_w);
                            Stack<String> _s = new Stack<String>();
                            _s.addAll(s);
                            _s.push(_w);
                            q.offer(_s);
                        }
                    }
                }
            }
        }

        return solution;
    }

    public WordLadder(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        
        HashSet<String> dictionary;

        try {
            dictionary = createDictionary("dictionary.txt");
            if (!validateWords(word1, word2, dictionary)) {
                this.ladder = "Wrong words.";
            } else {
                Stack<String> solution = findLadder(word1, word2, dictionary);
                if (solution.empty()) {
                    this.existed = false;
                    this.message = "No ladder from " + word1 + " to " + word2 + '.';
                } else {
                    this.ladder = "A ladder from " + word2 + " back to " + word1 + ":";
                    while (!solution.empty()) {
                        this.ladder += (' ' + solution.pop());
                    }
                }
            }
        } catch (Exception e) {
            this.existed = false;
            this.message = "Dictionary does not exist.";
        }
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    public String getLadder() {
        return ladder;
    }

    public boolean getExisted() {
        return existed;
    }

    public String getMessage() {
        return message;
    }
}
