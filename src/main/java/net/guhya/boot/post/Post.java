package net.guhya.boot.post;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class Post {
	
	@Id
	private long id;
	
	@NotBlank(message = "Title is required")
	private String title;
	
	public Post() {
	}

	public Post(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
