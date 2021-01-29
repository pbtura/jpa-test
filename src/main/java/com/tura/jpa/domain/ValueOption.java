package com.tura.jpa.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.persistence.Entity;


@Entity
@Table(name = "value_options")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/*
 * The idea behind this class is to provide a standard baseclass for all those objects which are presented as values in
 * dropdowns like gender, hinge type, etc. They almost always consist of just an id and value pair (usually the value is
 * a string like male, female, etc). Implementations can extend this base class and get stored in a common value options
 * table so they we don't have huge database sprawl from all these value object tables that are all pretty much the same
 * thing.
 */
public abstract class ValueOption<T> extends com.tura.jpa.domain.Entity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -922746827625702676L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	/**
	 * The main value being store
	 */
	@Column
	protected T value;
	@Column(name = "legacy_value")
	protected String legacyValue;
	/**
	 * This could be used for a more display friendly value
	 */
	@Column
	protected String detail;
	@Column(name = "deprecated")
	protected Boolean deprecated = false;

	public ValueOption()
	{
		super();
	}

	public ValueOption(Long id)
	{
		super(id);
	}

	public ValueOption(Long id, T value, String detail)
	{
		super(id);
		this.value = value;
		this.detail = detail;
	}

	public ValueOption(Long id, T value)
	{
		super(id);
		this.value = value;
	}

	public ValueOption(T value, String detail)
	{
		super();
		this.value = value;
		this.detail = detail;
	}

	public ValueOption(T value)
	{
		super();
		this.value = value;
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	@XmlElement
	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " [value=" + value + ", detail=" + detail + "]";
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

	@XmlElement
	public String getLegacyValue()
	{
		return legacyValue;
	}

	public void setLegacyValue(String legacyValue)
	{
		this.legacyValue = legacyValue;
	}

	public Boolean getDeprecated()
	{
		return deprecated;
	}

	@Override
	public String getDisplayString()
	{
		if (value != null)
		{
			return value.toString();
		}
		else
		{
			return "";
		}
	}
}
