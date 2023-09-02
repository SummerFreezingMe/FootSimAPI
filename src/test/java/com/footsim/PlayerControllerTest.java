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
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    Random r = new Random();

    @Test
    public void testGetPlayerById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/players/get/7", 1)
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
    public void testGetAllPlayers() throws Exception {
        final ResultActions result = mockMvc.perform(get("/players/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/players/update/1")
                .content("""
                        {
                          "id": 2,
                          "clubId": 1,
                                             "rating": 123,
                                             "name": "string",
                                             "position": "GOALKEEPER",
                                             "status": "ROSTER"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("BENCH"));
    }

    @Test
    public void testAddPlayer() throws Exception {
        String str = String.valueOf(r.nextInt(200));
        final ResultActions result = mockMvc.perform(post("/players/add")
                .content("""
                        {
                          "id": 1,
                          "clubId": 0,
                          "rating": 0,
                          "name": "%s",
                          "position": "GOALKEEPER",
                          "status": "ROSTER"
                        }""".formatted(str))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(0))
                .andExpect(jsonPath("$.rating").value(0))
                .andExpect(jsonPath("$.status").value("ROSTER"))
                .andExpect(jsonPath("$.name").value(str))
                .andExpect(jsonPath("$.position").value("GOALKEEPER"));
    }

    @Test
    public void testDeletePlayer() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/players/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void testTransferPlayerSuccessful() throws Exception {
        final ResultActions result = mockMvc.perform(post("/players/transfer")
                .content("""
                        {
                                 "playerId": 3,
                                 "clubFromId": 1,
                                 "clubToId": 2,
                                 "transferFee": 2,
                                 "transferDate": "2023-08-27T15:17:14.245Z"
                               }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(2))
                .andExpect(jsonPath("$.rating").value(133))
                .andExpect(jsonPath("$.status").value("ROSTER"))
                .andExpect(jsonPath("$.name").value("Neymar Jr."))
                .andExpect(jsonPath("$.position").value("FORWARD"));
    }


    @Test
    public void testPlayerChangeStatus() throws Exception {
        final ResultActions result = mockMvc.perform(get("/switch_status/2/BENCH")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("BENCH"));
    }


}

