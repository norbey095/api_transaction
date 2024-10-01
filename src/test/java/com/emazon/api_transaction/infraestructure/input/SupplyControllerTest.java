package com.emazon.api_transaction.infraestructure.input;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.handler.ISupplyHandler;
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


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SupplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISupplyHandler supplyHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private ArticleUpdateRequestDto request;

    @BeforeEach
    public void setUp() {
        request = new ArticleUpdateRequestDto(ConstantsInfraestructure.ARTICLE_ID
                , ConstantsInfraestructure.QUANTITY);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.AUX_WAREHOUSE})
    void testAddSupply_Success() throws Exception {
        Mockito.when(supplyHandler.addSupply(Mockito.any(ArticleUpdateRequestDto.class)))
                .thenReturn(new ResponseStockDto());

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_ADD_SUPPLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
