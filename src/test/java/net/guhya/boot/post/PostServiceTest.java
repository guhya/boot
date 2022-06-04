package net.guhya.boot.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

	@Autowired
	PostService service;
	
	@Autowired
	PostRepository repository;
	
	@BeforeEach
	void emptyTable() {
		repository.deleteAll();
	}
	
	@Test
	void givenOnePostShouldSave() {
		Post post = new Post("First post");
		
		Post saved = service.insert(post);
		
		assertEquals(1, service.countAll());
		assertEquals(saved.getTitle(), "First post");
	}

	@Test
	void givenTwoPostShouldSave() {
		Post post1 = new Post("First post");
		Post post2 = new Post("Second post");
		
		Post saved1 = service.insert(post1);
		Post saved2 = service.insert(post2);
		
		assertEquals(2, service.countAll());
		assertEquals(saved1.getTitle(), "First post");
		assertEquals(saved2.getTitle(), "Second post");
	}
	
	@Test
	void givenIdShouldGetCorrectPost() {
		Post post = new Post("First post");
		Post saved = service.insert(post);
		long id = saved.getId();
		
		Post result = service.selectById(id);
		
		assertEquals(result.getTitle(), "First post");		
	}
	
	@Test
	void givenPostShouldUpdateCorrectPost() {
		Post post = new Post("First post");
		Post saved = service.insert(post);
		saved.setTitle("Updated First post");
		Post updated = service.update(saved);
		long id = updated.getId();
		
		Post result = service.selectById(id);
		
		assertEquals(result.getTitle(), "Updated First post");
	}
	
}
