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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
public class GoalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetGoalById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/goals/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.authorId").value(7))
                .andExpect(jsonPath("$.assistId").value(2))
                .andExpect(jsonPath("$.minute").value(2))
                .andExpect(jsonPath("$.type").value("DEFAULT"));
    }

    @Test
    public void testGetAllGoals() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/goals/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateGoal() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/goals/update/1")
                .content("""
                        {
                           "matchId": 1,
                           "authorId": 5,
                           "minute": 78,
                           "type": "PENALTY"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.authorId").value(5))
                .andExpect(jsonPath("$.minute").value(78))
                .andExpect(jsonPath("$.type").value("PENALTY"));
    }

    @Test
    public void testAddGoal() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/goals/add")
                .content("""
                        {
                           "matchId": 1,
                           "authorId": 3,
                           "assistId": 2,
                           "minute": 44,
                           "type": "DEFAULT"
                         }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.authorId").value(3))
                .andExpect(jsonPath("$.assistId").value(2))
                .andExpect(jsonPath("$.minute").value(44))
                .andExpect(jsonPath("$.type").value("DEFAULT"));
    }

    @Test
    public void testDeleteGoal() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/goals/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void testTopScorers() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/goals/top_scorers/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void testTopAssistants() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/goals/top_assistants/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
