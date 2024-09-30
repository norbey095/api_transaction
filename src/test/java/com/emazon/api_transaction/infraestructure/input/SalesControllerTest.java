package com.emazon.api_transaction.infraestructure.input;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.application.handler.ISalesHandler;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISalesHandler salesHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private SalesRequestDto salesRequestDto;

    @BeforeEach
    public void setUp() {
        salesRequestDto = new SalesRequestDto(ConstantsInfraestructure.ARTICLE_ID
                , ConstantsInfraestructure.EMAIL, LocalDateTime.now(),ConstantsInfraestructure.ARTICLE_ID,
                ConstantsInfraestructure.QUANTITY);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.CLIENT})
    void testSaveSales_Success() throws Exception {
        List<SalesRequestDto> salesRequestDtoList = new ArrayList<>();
        salesRequestDtoList.add(salesRequestDto);

        Mockito.when(salesHandler.saveSales(salesRequestDtoList))
                .thenReturn(new ResponseStockDto());

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_ADD_SALES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salesRequestDtoList)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.CLIENT})
    void testDeleteRegistry_Success() throws Exception {
        String userName = ConstantsInfraestructure.USER_NAME;
        LocalDateTime buyDate =  LocalDateTime.now();

        Mockito.doNothing().when(salesHandler).deleteRegistry(userName,buyDate);

        mockMvc.perform(MockMvcRequestBuilders.delete(ConstantsInfraestructure.URL_DELETE_SALES+
                                ConstantsInfraestructure.SLASH+userName+ConstantsInfraestructure.SLASH+
                                buyDate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
