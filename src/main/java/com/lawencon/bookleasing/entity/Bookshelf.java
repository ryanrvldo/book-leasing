package com.lawencon.bookleasing.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Bookshelf {

	private Long id;
	private String code;
	private Integer rowPosition;
	private Integer columnPosition;

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

	public Integer getRowPosition() {
		return rowPosition;
	}

	public void setRowPosition(Integer rowPosition) {
		this.rowPosition = rowPosition;
	}

	public Integer getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(Integer columnPosition) {
		this.columnPosition = columnPosition;
	}

}
