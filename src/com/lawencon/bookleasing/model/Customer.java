package com.lawencon.bookleasing.model;

import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Customer {

	private Long id;
	private Profile profile;
	private LocalDateTime createdAt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
