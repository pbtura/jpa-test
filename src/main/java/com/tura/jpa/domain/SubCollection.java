package com.tura.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

@Entity(name = "SubCollection")
@Table(name = "subcollections")
@NamedQueries({
		@NamedQuery(name = "SubCollection.findAll", query = "select s from SubCollection s order by s.name asc", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true") }),
		@NamedQuery(name = "SubCollection.findByName", query = "select s from SubCollection s where s.name=:name"),
		@NamedQuery(name = "SubCollection.findByBrand", query = "select s from SubCollection s where s.brandId=:brandId"),
		@NamedQuery(name = "SubCollection.findByNameAndBrand", query = "select s from SubCollection s where s.brandId=:brandId and s.name=:name") })
public class SubCollection extends com.tura.jpa.domain.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8955297592660500204L;
	@Column
	private String name;
	@Column(name = "brand_id")
	protected Long brandId;

	public SubCollection()
	{
		super();
	}

	public SubCollection(Long id)
	{
		super(id);
	}

	public SubCollection(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getBrandId()
	{
		return brandId;
	}

	public void setBrandId(Long brandId)
	{
		this.brandId = brandId;
	}
}
