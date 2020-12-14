package com.lawencon.bookleasing.model;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Bookshelf {

	private Long id;
	private String code;
	private Integer rowPositon;
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

	public Integer getRowPositon() {
		return rowPositon;
	}

	public void setRowPositon(Integer rowPositon) {
		this.rowPositon = rowPositon;
	}

	public Integer getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(Integer columnPosition) {
		this.columnPosition = columnPosition;
	}

}
