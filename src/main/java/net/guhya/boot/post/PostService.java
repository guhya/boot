package net.guhya.boot.post;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post insert(Post post) {
		Post saved = repository.save(post);
		
		return saved;
	}
	
	public Post selectById(long id) {
		Optional<Post> post = repository.findById(id);
		if (post.isPresent()) 
			return post.get();
		else
			return null;
	}
	
	public Post update(Post post) {
		Post saved = repository.save(post);
		
		return saved;
	}

	public long countAll() {
		long count = repository.count();
				
		return count;
	}
}
