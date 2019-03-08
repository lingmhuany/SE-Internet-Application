package wordladder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Stack;

public class WordLadderTest {
    HashSet<String> dictionary;

    @Before
    public void shouldCreateADictionary()  {
        try {
            dictionary = WordLadder.createDictionary("dictionary.txt");
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void ShouldNotCreateADictionary() {
        try {
            dictionary = WordLadder.createDictionary("dict");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void validateWordsShouldAnswerWithTrue() {
        assertTrue(WordLadder.validateWords("cat", "dog", dictionary));
        assertTrue(WordLadder.validateWords("data", "cake", dictionary));
    }

    @Test
    public void validateWordsShouldAnswerWithFalse() {
        assertFalse(WordLadder.validateWords("cold", "hot", dictionary));
        assertFalse(WordLadder.validateWords("qwer", "tyui", dictionary));
        assertFalse(WordLadder.validateWords("cat", "cat", dictionary));
    }

    @Test
    public void CanFindALadder() {
        Stack<String> ladder;

        ladder = WordLadder.findLadder("cat", "dog", dictionary);
        assertTrue(ladder.pop().equals("dog"));
        assertTrue(ladder.pop().equals("dot"));
        assertTrue(ladder.pop().equals("cot"));
        assertTrue(ladder.pop().equals("cat"));
        assertTrue(ladder.empty());

        ladder = WordLadder.findLadder("data", "cake", dictionary);
        assertTrue(ladder.pop().equals("cake"));
        assertTrue(ladder.pop().equals("cate"));
        assertTrue(ladder.pop().equals("date"));
        assertTrue(ladder.pop().equals("data"));
        assertTrue(ladder.empty());
    }

    @Test
    public void CannotFindALadder() {
        Stack<String> ladder;

        ladder = WordLadder.findLadder("alongside", "amazement", dictionary);
        assertTrue(ladder.empty());

        ladder = WordLadder.findLadder("interview", "companion", dictionary);
        assertTrue(ladder.empty());
    }
}
