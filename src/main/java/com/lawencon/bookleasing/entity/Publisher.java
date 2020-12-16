package com.lawencon.bookleasing.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Publisher {

	private Long id;
	private String code;
	private String name;
	private String city;

	public Publisher() {
	}

	public Publisher(String code, String name, String city) {
		this.code = code;
		this.name = name;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
