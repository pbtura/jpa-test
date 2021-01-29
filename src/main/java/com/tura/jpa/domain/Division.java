package com.tura.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity(name = "Division")
@DiscriminatorValue("DIVISION")
@NamedQueries({ @NamedQuery(name = "Division.findAll", query = "select d from Division as d") })
public class Division extends ValueOption<String>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6794553675614244712L;
	public static final String BBH_DIVISION = "02";
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "sub_collection_id")
	protected SubCollection subCollection;

	public Division()
	{
		super();
	}

	public Division(String value, String detail)
	{
		super(value, detail);
	}

	public boolean isBBH()
	{
		if (value != null && value.equals(BBH_DIVISION))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public SubCollection getSubCollection()
	{
		return subCollection;
	}

	public void setSubCollection(SubCollection subCollection)
	{
		this.subCollection = subCollection;
	}
}
