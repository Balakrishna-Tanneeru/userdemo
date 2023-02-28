package com.bk.demo.Controller;

import com.bk.demo.DemoApplication;
import com.bk.demo.model.UserRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
        @Autowired
        private MockMvc mvc;
        @MockBean
        private UserController userController;

        @Test
        void testGetAllUser() throws Exception {

            UserRecord userRecord = getUserRecord();
            List<UserRecord> userRecords = new ArrayList<>();
            userRecords.add(userRecord);
            given(userController.getAllUser()).willReturn(userRecords);
            mvc.perform(get("/").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name", is(userRecord.getName())));
        }

    @Test
    public void testAddUser() throws Exception {
        UserRecord employee = getUserRecord();
        doNothing().when(userController).addUser(employee);
        mvc.perform(post("/add-user/").content(asJson(employee)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

   /* @Test
    public void testUpdateUser() throws Exception {
        UserRecord employee = getUserRecord();
        doNothing().when(userController).replaceEmployee(employee,1);
        mvc.perform(put("/updateUser/"+employee.getId())).content(asJson(employee)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }*/

        @Test
        void getUserById() {
        }

    @Test
    public void testDeleteUser() throws Exception {
        UserRecord user = getUserRecord();
        doNothing().when(userController).deleteUser(001);
        mvc.perform(delete("/deleteUser/" + user.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

        private UserRecord getUserRecord() {
            UserRecord userRecord = new UserRecord();
            userRecord.setName("BK");
            userRecord.setEmail("bk@gmail.com");
            userRecord.setId(002);
            return userRecord;
        }
        private String asJson(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}