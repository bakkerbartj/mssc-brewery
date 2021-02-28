package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.mockito.BDDMockito.*; //for BDD
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;  //for put()
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;  //for post()
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  //for get(url)
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;      //for content() and status()
import static org.hamcrest.Matchers.is; //for is

@WebMvcTest(BeerController.class)
@RunWith(SpringRunner.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;
    BeerDto validBeer;

    @Before
    public void setUp() throws Exception {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Cascade")
                .beerStyle("Tasmanian")
                .upc(123456789012L)
                .build();
    }


    @Test
    public void getBeer() throws Exception{
        if(validBeer == null) {
            System.out.println("Out of beer ...");
        } else {
            System.out.println("Beer on tap: " + validBeer.getBeerName());
        }
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Cascade")));
    }

    @Test
    public void handlePost() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("Grolsch").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handleUpdate() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + validBeer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}