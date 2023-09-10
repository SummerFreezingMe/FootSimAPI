package com.footsim;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hamcrest.core.IsNull;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DatabaseSetup(value = "/match_simulation.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FootSimApiApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners(value = {
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class MatchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMatchById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/matches/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.date").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.homeClubId").value(1))
                .andExpect(jsonPath("$.awayClubId").value(2))
                .andExpect(jsonPath("$.homeGoals").value(4))
                .andExpect(jsonPath("$.awayGoals").value(4));
    }

    @Test
    public void testGetAllMatches() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/matches/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateMatch() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/matches/update/1")
                .content("""
                        {
                            "seasonId": 1,
                            "homeClubId": 1,
                            "awayClubId": 2,
                            "homeGoals": 0,
                            "date": "2023-09-10T12:50:42.688Z",
                            "awayGoals": 0
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.date").value("2023-09-10T12:50:42.688"))
                .andExpect(jsonPath("$.homeClubId").value(1))
                .andExpect(jsonPath("$.awayClubId").value(2))
                .andExpect(jsonPath("$.homeGoals").value(0))
                .andExpect(jsonPath("$.awayGoals").value(0));
    }

    @Test
    public void testAddMatch() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/matches/add")
                .content("""
                        {
                            "id": 0,
                            "seasonId": 1,
                            "homeClubId": 2,
                            "awayClubId": 1,
                            "homeGoals": 2,
                            "date": "2023-09-10T12:50:42.688Z",
                            "awayGoals": 2
                         }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seasonId").value(1))
                .andExpect(jsonPath("$.date").value("2023-09-10T12:50:42.688"))
                .andExpect(jsonPath("$.homeClubId").value(2))
                .andExpect(jsonPath("$.awayClubId").value(1))
                .andExpect(jsonPath("$.homeGoals").value(2))
                .andExpect(jsonPath("$.awayGoals").value(2));
    }

    @Test
    public void testDeleteMatch() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/matches/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void testSimulateMatch() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/matches/simulate/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
//fails with a 403 since there is roster incompleteness
    @Test
    public void testSimulateMatchFailure() throws Exception {
        mockMvc.perform(delete("/api/players/delete/12"));
        final ResultActions result = mockMvc.perform(get("/api/matches/simulate/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isForbidden());
    }
}
