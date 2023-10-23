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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class ClubControllerTest {
    @Autowired
    private MockMvc mockMvc;

    Random r = new Random();

    @Test
    public void testGetClubById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/clubs/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Barcelona"))
                .andExpect(jsonPath("$.rating").value(1111))
                .andExpect(jsonPath("$.leagueId").value(1))
                .andExpect(jsonPath("$.balance").value(2));
    }

    @Test
    public void testGetAllClubs() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/clubs/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateClub() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/clubs/update/1")
                .content("""
                        {
                          "id": 0,
                                             "name": "string",
                                             "rating": 228,
                                             "stadium": "71",
                                             "description": "string",
                                             "leagueId": 0,
                                             "image": "string",
                                             "balance": 0
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("string"))
                .andExpect(jsonPath("$.rating").value(228))
                .andExpect(jsonPath("$.stadium").value("71"))
                .andExpect(jsonPath("$.description").value("string"))
                .andExpect(jsonPath("$.leagueId").value(0))
                .andExpect(jsonPath("$.image").value("string"))
                .andExpect(jsonPath("$.balance").value(0));
    }

    @Test
    public void testAddClub() throws Exception {
        String str = String.valueOf(r.nextInt(200));
        final ResultActions result = mockMvc.perform(post("/api/clubs/add")
                .content("""
                        {
                          "id": 0,
                                              "name": "string",
                                              "rating": 911,
                                              "stadium": %s,
                                              "description": "string",
                                              "leagueId": 0,
                                              "image": "string",
                                              "balance": 0
                        }""".formatted(str))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("string"))
                .andExpect(jsonPath("$.rating").value(911))
                .andExpect(jsonPath("$.stadium").value(str))
                .andExpect(jsonPath("$.description").value("string"))
                .andExpect(jsonPath("$.leagueId").value(0))
                .andExpect(jsonPath("$.image").value("string"))
                .andExpect(jsonPath("$.balance").value(0));
    }

    @Test
    public void testDeleteClub() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/clubs/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
