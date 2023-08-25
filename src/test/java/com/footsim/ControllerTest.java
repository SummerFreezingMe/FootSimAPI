package com.footsim;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DatabaseSetup(value = "/topactions.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootSimApiApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners(value = {
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetVenueById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/players/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(1))
                .andExpect(jsonPath("$.rating").value(111))
                .andExpect(jsonPath("$.status").value("ROSTER"))
                .andExpect(jsonPath("$.name").value("Lionel Messi"))
                .andExpect(jsonPath("$.position").value("FORWARD"));
    }


    @Test
    public void testAddPlayer() throws Exception {
        final ResultActions result = mockMvc.perform(post("/players/add")
                .content("""
                        {
                          "id": 0,
                          "clubId": 0,
                          "rating": 0,
                          "name": "string",
                          "position": "GOALKEEPER",
                          "status": "ROSTER"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(0))
                .andExpect(jsonPath("$.rating").value(0))
                .andExpect(jsonPath("$.status").value("ROSTER"))
                .andExpect(jsonPath("$.name").value("string"))
                .andExpect(jsonPath("$.position").value("GOALKEEPER"));
    }
}

