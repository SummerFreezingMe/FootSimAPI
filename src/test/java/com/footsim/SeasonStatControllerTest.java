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
public class SeasonStatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetSeasonStatById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/season_stats/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.clubId").value(1))
                .andExpect(jsonPath("$.points").value(0))
                .andExpect(jsonPath("$.wins").value(0))
                .andExpect(jsonPath("$.draws").value(0))
                .andExpect(jsonPath("$.defeats").value(0))
                .andExpect(jsonPath("$.goalsScored").value(0))
                .andExpect(jsonPath("$.goalsConceded").value(0));
    }

    @Test
    public void testGetAllSeasonStats() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/season_stats/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateSeasonStat() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/season_stats/update/1")
                .content("""
                        {
                          "id": 0,
                          "seasonId": 1,
                          "clubId": 1,
                          "points": 3,
                          "wins": 1,
                          "draws": 0,
                          "defeats": 0,
                          "goalsScored": 2,
                          "goalsConceded": 1
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.clubId").value(1))
                .andExpect(jsonPath("$.points").value(3))
                .andExpect(jsonPath("$.wins").value(1))
                .andExpect(jsonPath("$.draws").value(0))
                .andExpect(jsonPath("$.defeats").value(0))
                .andExpect(jsonPath("$.goalsScored").value(2))
                .andExpect(jsonPath("$.goalsConceded").value(1));
    }

    @Test
    public void testAddSeasonStat() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/season_stats/add")
                .content("""
                        {
                          "id": 0,
                          "seasonId": 1,
                          "clubId": 2,
                          "points": 0,
                          "wins": 0,
                          "draws": 0,
                          "defeats": 1,
                          "goalsScored": 1,
                          "goalsConceded": 2
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.clubId").value(2))
                .andExpect(jsonPath("$.points").value(0))
                .andExpect(jsonPath("$.wins").value(0))
                .andExpect(jsonPath("$.draws").value(0))
                .andExpect(jsonPath("$.defeats").value(1))
                .andExpect(jsonPath("$.goalsScored").value(1))
                .andExpect(jsonPath("$.goalsConceded").value(2));
    }

    @Test
    public void testDeleteSeasonStat() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/season_stats/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void testInitSeasonStat() throws Exception {
        mockMvc.perform(delete("/api/season_stats/delete/1"));
        final ResultActions result = mockMvc.perform(get("/api/season_stats/init_season/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
