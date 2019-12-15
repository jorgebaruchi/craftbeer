package com.beerhouse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.beerhouse.domain.Beer;
import com.beerhouse.domain.BeerCategory;
import com.beerhouse.repository.BeerRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
@AutoConfigureTestDatabase
public class ApplicationTests {
	
	@Autowired
    private MockMvc mvc;

	@Autowired
    private BeerRepository repository;
    
    @Before
    public void clearDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateBeer() throws IOException, Exception {
 	    BeerCategory beerCategory = new BeerCategory();
 	    beerCategory.setCategoryId(1L);
 		
 	    Beer beer = new Beer();
 	    beer.setBeerName("Brahma");
 	    beer.setAlcoholContent("4.5");
 	    beer.setCategory(beerCategory);
 	    beer.setIngredients("milho");
 	    beer.setPrice(new BigDecimal(2.00));
 	    
        mvc.perform(post("/beers").contentType(MediaType.APPLICATION_JSON)
        		.content(toJson(beer)));

        List<Beer> found = repository.findAll();
        assertThat(found).extracting(Beer::getBeerName).containsOnly("Brahma");
    }

    @Test
    public void givenBeesList_whenGet_thenStatus200() throws Exception {
        createTestBeer("Heineken");
        createTestBeer("Devassa");

        mvc.perform(get("/beers").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
			.andExpect(jsonPath("$[0].beerName", is("Heineken")))
			.andExpect(jsonPath("$[1].beerName", is("Devassa")));
    }

    private void createTestBeer(String name) {
 	    BeerCategory beerCategory = new BeerCategory();
 	    beerCategory.setCategoryId(2L);
 	    
 	    Beer beer = new Beer();
 	    beer.setBeerName(name);
 	    beer.setAlcoholContent("4.5");
 	    beer.setCategory(beerCategory);
 	    beer.setIngredients("Agua, malte e lúpulo");
 	    beer.setPrice(new BigDecimal(4.00));
 	    
 	    repository.saveAndFlush(beer);
    }
    
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }


}