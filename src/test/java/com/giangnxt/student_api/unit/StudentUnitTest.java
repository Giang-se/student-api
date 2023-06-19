package com.giangnxt.student_api.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giangnxt.student_api.model.entity.Student;
import com.giangnxt.student_api.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class StudentUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository mockStudentRepository;

    private String studentPostPayload = "{\"id\":1000,\"firstName\":\"Giang\",\"lastName\":\"NXT\",\"studentClass\":\"1 A\"}";

    public Student getStudent(String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(payload, Student.class);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Mockito.when(mockStudentRepository.save(Mockito.any(Student.class))).thenReturn(getStudent(studentPostPayload));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(studentPostPayload);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        JSONAssert.assertEquals(studentPostPayload, response.getContentAsString(), false);
    }

    @Test
    public void testGetStudent() throws Exception {
        Mockito.when(mockStudentRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(getStudent(studentPostPayload)));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(studentPostPayload, response.getContentAsString(), false);
    }

}
