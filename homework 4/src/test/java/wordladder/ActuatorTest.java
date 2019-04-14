package wordladder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ActuatorTest {
        @Autowired
        private WebApplicationContext webApplicationContext;
        private MockMvc mockMvc;

        @Before
        public void setUp() throws Exception {
                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        @Test
        public void shouldReturn200() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/actuator"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
                mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
                mockMvc.perform(MockMvcRequestBuilders.get("/actuator/info"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
                mockMvc.perform(MockMvcRequestBuilders.get("/actuator/try"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("20190414"))
                                .andReturn();
        }
}