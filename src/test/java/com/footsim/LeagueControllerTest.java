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
public class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetLeagueById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/leagues/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.participants").value(20))
                .andExpect(jsonPath("$.name").value("La Liga"))
        ;
    }

    @Test
    public void testGetAllLeagues() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/leagues/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateLeague() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/leagues/update/1")
                .content("""
                        {
                           "participants": 20,
                           "name": "Serie A"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.participants").value(20))
                .andExpect(jsonPath("$.name").value("Serie A"));
    }

    @Test
    public void testAddLeague() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/leagues/add")
                .content("""
                        {
                           "participants": 16,
                           "name": "Russian Premier League"
                         }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.participants").value(16))
                .andExpect(jsonPath("$.name").value("Russian Premier League"));
    }

    @Test
    public void testDeleteLeague() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/leagues/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
