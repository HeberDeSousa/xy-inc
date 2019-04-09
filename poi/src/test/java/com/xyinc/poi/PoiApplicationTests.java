package com.xyinc.poi;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyinc.poi.model.Poi;
import com.xyinc.poi.model.Referencia;
import com.xyinc.poi.repository.PoiRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PoiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PoiRepository poiRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	static private String nome = "Teste";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(new Poi(1L, "Lanchonete", 27, 12));
		pois.add(new Poi(2L, "Posto", 31, 18));
		pois.add(new Poi(3L, "Joalheria", 15, 12));
		pois.add(new Poi(4L, "Floricultura", 19, 21));
		pois.add(new Poi(5L, "Pub", 12, 8));
		pois.add(new Poi(6L, "Supermercado", 23, 6));
		pois.add(new Poi(7L, "Churrascaria", 28, 2));
		when(poiRepository.findAll()).thenReturn(pois);
		Poi poi = new Poi(8L, nome, 1, 1);
		when(poiRepository.saveAndFlush(any(Poi.class))).thenReturn(poi);
	}

	@Test
	public void testeCadastrar() throws Exception {
		Poi poi = new Poi(null, nome, 1, 1);
		mvc.perform(post("/poi/v1/cadastrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(poi)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.nome", is(poi.getNome())))
				.andReturn();
	}

	@Test
	public void testeListar() throws Exception {
		mvc.perform(get("/poi/v1/listar").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(7)))
				.andExpect(jsonPath("$[0].nome", is("Lanchonete")))
				.andExpect(jsonPath("$[1].nome", is("Posto")))
				.andExpect(jsonPath("$[2].nome", is("Joalheria")))
				.andExpect(jsonPath("$[3].nome", is("Floricultura")))
				.andExpect(jsonPath("$[4].nome", is("Pub")))
				.andExpect(jsonPath("$[5].nome", is("Supermercado")))
				.andExpect(jsonPath("$[6].nome", is("Churrascaria")))
				.andReturn();
	}

	@Test
	public void testeListarPorProximidade() throws Exception {
		Referencia ref = new Referencia(20, 10, 10);
		
		mvc.perform(post("/poi/v1/listarPorProximidade")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ref)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].nome", is("Lanchonete")))
				.andExpect(jsonPath("$[1].nome", is("Joalheria")))
				.andExpect(jsonPath("$[2].nome", is("Pub")))
				.andExpect(jsonPath("$[3].nome", is("Supermercado")))
				.andReturn();
	}

}
