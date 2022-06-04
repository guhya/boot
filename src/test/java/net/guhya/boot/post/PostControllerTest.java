package net.guhya.boot.post;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper om;

	@Test
	void givenGetAndNoParamPostShouldReturnString() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/v1/post")
				.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("Hello")));
	}
	
	@Test
	void givenPostAndNoBodyPostShouldReturnUnsupportedMediaType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
		)
		.andExpect(status().isUnsupportedMediaType())
		.andExpect(content().string(equalTo("")));
	}
	
	@Test
	void givenPostAndPlainBodyPostShouldReturnUnsupportedMediaType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.TEXT_PLAIN)
		)
		.andExpect(status().isUnsupportedMediaType())
		.andExpect(content().string(equalTo("")));
	}
	
	@Test
	void givenPostAndFormBodyPostShouldReturnUnsupportedMediaType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		)
		.andExpect(status().isUnsupportedMediaType())
		.andExpect(content().string(equalTo("")));
	}

	@Test
	void givenPostAndNoJsonBodyPostShouldReturnBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isBadRequest())
		.andExpect(content().string(equalTo("")));
	}

	@Test
	void givenPostAndEmptyJsonBodyPostShouldReturnBadRequest() throws Exception {
		String json = "{}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
		.andExpect(status().isBadRequest())
		.andExpect(content().json("{\"status\" : 400}"));
	}

	@Test
	void givenPostAndCorrectJsonBodyNoQuotePostShouldReturnBadRequest() throws Exception {
		String json = "{ title : First Post }";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
		.andExpect(status().isBadRequest())
		.andExpect(content().string(equalTo("")));
	}

	@Test
	void givenPostAndCorrectJsonBodyPostShouldReturnOk() throws Exception {
		String json = "{ \"title\" : \"First Post\" }";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/v1/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json(json));
	}
}
