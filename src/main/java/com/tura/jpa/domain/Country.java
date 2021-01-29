package com.tura.jpa.domain;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


/**
 * Represents a country
 * 
 */
@Entity(name = "Country")
@Table(name = "countries")
@NamedQueries({ @NamedQuery(name = "Country.findAll", query = "select c from Country as c"),
		@NamedQuery(name = "Country.findByName", query = "select c from Country as c where c.name=:name"),
		@NamedQuery(name = "Country.getStates", query = "select OBJECT(x) from Country as entity JOIN entity.states as x  where entity.id=:id order by entity.name asc") })
public class Country extends com.tura.jpa.domain.Entity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 948289514140930228L;
	// relationships
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column
	private String name;
	@Column
	private String abbreviation;
	@Column
	private String code;
	@Column(name = "jobson_code", nullable = true)
	private String jobsonCode;
	@Column(name = "product_tariff_percentage", precision = 19, scale = 18)
	private BigDecimal productTariffPercentage;
	@Column(name = "accessory_tariff_percentage", precision = 19, scale = 18)
	private BigDecimal accessoryTariffPercentage;
	// constants for determining category based on the id of the country in the
	// database
	@Transient
	public static final String UNKNOWN = "Unknown";
	@Transient
	public static final String US = "U.S.A.";
	@Transient
	public static final String CANADA = "Canada";
	@Transient
	public static final String CHINA = "China";
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	@OrderBy(value = "name asc")
	private List<StateProvince> states;
	
	@Column
	protected Integer currencyId;

	public Country()
	{
		super();
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

	public String getAbbreviation()
	{
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tura.common.domain.CountryData#getName()
	 */
	@NotNull
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name != null)
		{
			name = name.trim();
		}
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tura.common.domain.CountryData#getCode()
	 */
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public List<StateProvince> getStates()
	{
		if (states == null)
		{
			states = new ArrayList<StateProvince>();
		}
		return states;
	}

	public void setStates(List<StateProvince> states)
	{
		this.states = states;
	}

	public String getJobsonCode()
	{
		return jobsonCode;
	}

	public BigDecimal getAccessoryTariffPercentage()
	{
		return accessoryTariffPercentage;
	}

	public BigDecimal getProductTariffPercentage()
	{
		return productTariffPercentage;
	}

	public Integer getCurrencyId()
	{
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId)
	{
		this.currencyId = currencyId;
	}

	public boolean isUS()
	{
		if (this.name != null && this.name.trim().equals(US))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isCanada()
	{
		if (this.name != null && this.name.trim().equals(CANADA))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isChina()
	{
		if (this.name != null && this.name.trim().equals(CHINA))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isUnknown()
	{
		if (this.name != null && this.name.trim().equals(UNKNOWN))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isDomestic()
	{
		if (isUS() || isCanada())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isAbroad()
	{
		if (!isDomestic() && !isUnknown())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void frontLoadStates()
	{
		if (!getStates().isEmpty())
		{
			getStates().get(0);
		}
	}

	@Override
	public String getDisplayString()
	{
		return getName();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Country [");
		if (abbreviation != null)
		{
			builder.append("abbreviation=");
			builder.append(abbreviation);
			builder.append(", ");
		}
		if (code != null)
		{
			builder.append("code=");
			builder.append(code);
			builder.append(", ");
		}
		if (name != null)
		{
			builder.append("name=");
			builder.append(name);
		}
		builder.append("]");
		return builder.toString();
	}
}
