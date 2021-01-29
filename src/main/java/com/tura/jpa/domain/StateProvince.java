package com.tura.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * Represents a state or province
 * 
 * @author gdunkle
 * 
 */
@Entity(name = "StateProvince")
@Table(name = "states")
@NamedQueries({
		@NamedQuery(name = "StateProvince.findAll", query = "select s from StateProvince as s order by s.name asc", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true") }) })
@XmlType(name = "state")
@XmlRootElement(name = "state")
@XmlAccessorType(XmlAccessType.NONE)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class StateProvince extends com.tura.jpa.domain.Entity
{
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2520114161170021060L;
	// relationships
	// attributes
	@XmlElement
	private String name;
	@XmlElement
	private String abbreviation;
	@Column(name = "collect_usage_tax")
	@XmlElement
	private Boolean collectUsageTax;
	@Column(name = "collect_sales_tax")
	@XmlElement
	private Boolean collectSalesTax;
	@Column(name = "report_sales")
	@XmlElement
	private Boolean reportSales;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public StateProvince()
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tura.common.domain.StateData#getAbbreviation()
	 */
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
	 * @see com.tura.common.domain.StateData#getName()
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
			// name=name.trim();
			this.name = name.trim();
		}
		else
		{
			this.name = name;
		}
	}

	public Boolean getCollectUsageTax()
	{
		return collectUsageTax;
	}

	public void setCollectUsageTax(Boolean collectTax)
	{
		this.collectUsageTax = collectTax;
	}

	public Boolean getReportSales()
	{
		return reportSales;
	}

	public void setReportSales(Boolean reportSales)
	{
		this.reportSales = reportSales;
	}

	public Boolean getCollectSalesTax()
	{
		return collectSalesTax;
	}

	public void setCollectSalesTax(Boolean collectSalesTax)
	{
		this.collectSalesTax = collectSalesTax;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}
}
