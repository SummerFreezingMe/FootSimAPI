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
public class CoachControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Random r = new Random();

    @Test
    public void testGetCoachById() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/coaches/get/1", 1)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(1))
                .andExpect(jsonPath("$.rating").value(150))
                .andExpect(jsonPath("$.name").value("Pep Guardiola"));
    }

    @Test
    public void testGetAllCoaches() throws Exception {
        final ResultActions result = mockMvc.perform(get("/api/coaches/get_all", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateCoach() throws Exception {
        final ResultActions result = mockMvc.perform(put("/api/coaches/update/2")
                .content("""
                        {
                          "id": 2,
                          "clubId": 2,
                          "rating": 123,
                          "name": "Stanislav Cherchesov"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(2))
                .andExpect(jsonPath("$.rating").value(123))
                .andExpect(jsonPath("$.name").value("Stanislav Cherchesov"));
    }

    @Test
    public void testAddCoach() throws Exception {
        String str = String.valueOf(r.nextInt(200));
        final ResultActions result = mockMvc.perform(post("/api/coaches/add")
                .content("""
                        {
                          "id": 3,
                          "clubId": 1,
                          "rating": 99,
                          "name": "%s"
                        }""".formatted(str))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(1))
                .andExpect(jsonPath("$.rating").value(99))
                .andExpect(jsonPath("$.name").value(str));
    }

    @Test
    public void testDeleteCoach() throws Exception {
        final ResultActions result = mockMvc.perform(delete("/api/coaches/delete/1")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void testTransferCoachSuccessful() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/coaches/transfer")
                .content("""
                        {
                                 "personId": 1,
                                 "clubFromId": 1,
                                 "clubToId": 2,
                                 "transferFee": 2,
                                 "transferDate": "2023-08-27T15:17:14.245Z"
                               }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }


    @Test
    public void testCoachRelease() throws Exception {
        final ResultActions result = mockMvc.perform(post("/api/coaches/release")
                .content("""
                        {
                          "id": 1,
                          "clubId": 1,
                          "rating": 99,
                          "name": "Pep Guardiola"
                        }""")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clubId").value(IsNull.nullValue()));
    }

}
