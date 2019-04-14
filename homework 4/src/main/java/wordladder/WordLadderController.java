package wordladder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordLadderController {

    @GetMapping("/wordladder")
    public String greeting(@RequestParam(value="word1") String word1, @RequestParam(value="word2") String word2) {
        WordLadder wordLadder = new WordLadder(word1, word2);
        if (!wordLadder.getExisted()) {
            return wordLadder.getMessage();
        } else {
            return wordLadder.getLadder();
        }
    }
}
