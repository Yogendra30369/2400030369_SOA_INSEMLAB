package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RbacDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void adminCanAccessAdminEndpoint() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard").with(httpBasic("admin", "admin123")))
            .andExpect(status().isOk());
    }

    @Test
    void userCannotAccessAdminEndpoint() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard").with(httpBasic("user", "user123")))
            .andExpect(status().isForbidden());
    }

    @Test
    void userCanAccessUserEndpoint() throws Exception {
        mockMvc.perform(get("/api/user/profile").with(httpBasic("user", "user123")))
            .andExpect(status().isOk());
    }

    @Test
    void adminCannotAccessUserEndpoint() throws Exception {
        mockMvc.perform(get("/api/user/profile").with(httpBasic("admin", "admin123")))
            .andExpect(status().isForbidden());
    }

    @Test
    void unauthenticatedRequestIsDenied() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard"))
            .andExpect(status().isUnauthorized());
    }
}
