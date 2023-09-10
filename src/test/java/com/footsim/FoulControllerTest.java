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

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class FoulControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFoulById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/fouls/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.playerId").value(2))
                .andExpect(jsonPath("$.minute").value(5))
                .andExpect(jsonPath("$.type").value("YELLOW_CARD"));
    }

    @Test
    public void testGetAllFouls() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/fouls/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateFoul() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/fouls/update/1")
                .content("""
                        {
                           "matchId": 1,
                           "playerId": 7,
                           "minute": 72,
                           "type": "NO_CARD"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.playerId").value(7))
                .andExpect(jsonPath("$.minute").value(72))
                .andExpect(jsonPath("$.type").value("NO_CARD"));
    }

    @Test
    public void testAddFoul() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/fouls/add")
                .content("""
                        {
                           "matchId": 1,
                           "playerId": 4,
                           "minute": 47,
                           "type": "YELLOW_CARD"
                         }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matchId").value(1))
                .andExpect(jsonPath("$.playerId").value(4))
                .andExpect(jsonPath("$.minute").value(47))
                .andExpect(jsonPath("$.type").value("YELLOW_CARD"));
    }

    @Test
    public void testDeleteFoul() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/fouls/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
