package com.tura.jpa.dto;

import java.io.Serializable;


public class SearchDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5811565402274644011L;
	public Long id;
	public String model;
	public Long subcollectionId;

	public SearchDTO()
	{
		super();
	}

	public SearchDTO(Long id, String model, Long subcollectionId)
	{
		super();
		this.id = id;
		this.model = model;
		this.subcollectionId = subcollectionId;
	}

}
