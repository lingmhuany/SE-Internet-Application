package wordladder;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class WordLadder {
    public static HashSet<String> createDictionary(String fileName) throws Exception {
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

    public static boolean validateWords(String word1, String word2, HashSet<String> dictionary) {
        if (word1.length() != word2.length()) {
            System.out.println("The two words must be the same length.");
            System.out.println();
            return false;
        } else if (!dictionary.contains(word1) || !dictionary.contains(word2)) {
            System.out.println("The two words must be found in the dictionary.");
            System.out.println();
            return false;
        } else if (word1.equals(word2)) {
            System.out.println("The two words must be differnt.");
            System.out.println();
            return false;
        } else {
            return true;
        }
    }

    public static Stack<String> findLadder(String word1, String word2, HashSet<String> dictionary) {
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

    public static void main(String args[]) {
        BufferedReader br;
        String fileName;
        HashSet<String> dictionary;
        String word1, word2;

        // generate the dictionary
        while (true) {
            try {
                System.out.print("Please input the file name of the dictionary: ");
                br = new BufferedReader(new InputStreamReader(System.in));
                fileName = br.readLine();
                dictionary = createDictionary(fileName);
                break;
            } catch (Exception e) {
                System.out.println("Failed.");
                System.out.println();
            }
        }

        // validate the words
        while (true) {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    System.out.print("Word 1: ");
                    word1 = br.readLine();
                    System.out.print("Word 2: ");
                    word2 = br.readLine();
                } catch (Exception e) {
                    System.out.println("Failed.");
                    System.out.println();
                    continue;
                }
                if (!validateWords(word1, word2, dictionary)) {
                    continue;
                } else {
                    break;
                }
            }

            Stack<String> solution = findLadder(word1, word2, dictionary);

            // output the result
            if (solution.empty()) {
                System.out.println("No ladder from " + word1 + " to " + word2 + '.');
            } else {
                System.out.print("A ladder from " + word2 + " back to " + word1 + ':');
                while (!solution.empty()) {
                    System.out.print(' ' + solution.pop());
                }
                System.out.println();
                System.out.println();
            }

            // ask whether the user wants another try
            String flag = "";
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    System.out.print("Do you want to have another try? (Y/N) ");
                    flag = br.readLine();
                    if (flag.equals("Y") || flag.equals("N")) {
                        System.out.println();
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Failed.");
                    break;
                }
            }
            if (flag.equals("N")) {
                break;
            }
        }
    }
}
