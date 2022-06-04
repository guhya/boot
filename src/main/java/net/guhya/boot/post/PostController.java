package net.guhya.boot.post;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	PostService postService;
	
	@GetMapping("/v1/post")
	public String post() {
		logger.debug("Hello");
		
		return "Hello";
	}
	
	@PostMapping(value = "/v1/post", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Post create(@Valid @RequestBody Post post) {
		Post saved = postService.insert(post);
		
		return saved;
	}
}
