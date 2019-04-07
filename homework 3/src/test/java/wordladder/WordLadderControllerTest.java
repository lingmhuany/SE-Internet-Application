package wordladder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class WordLadderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldAnswerWithMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "cold").param("word2", "hot")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("The two words must be the same length."))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "qwer").param("word2", "tyui")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("The two words must be found in the dictionary."))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "cat").param("word2", "cat")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("The two words must be differnt."))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "alongside")
                .param("word2", "amazement").accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("No ladder from alongside to amazement."))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "interview")
                .param("word2", "companion").accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("No ladder from interview to companion."))
                .andReturn();
    }

    @Test
    public void shouldAnswerWithLadder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "dog").param("word2", "cat")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("A ladder from cat back to dog: cat cag cog dog"))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/wordladder").param("word1", "data").param("word2", "cake")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.content().string("A ladder from cake back to data: cake cate date data"))
                .andReturn();
    }
}