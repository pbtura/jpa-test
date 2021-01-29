package com.tura.jpa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class Entity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3120685239728510499L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	protected Date created;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	protected Date updated;
	@Version
	@Column(name = "lock_version")
	protected Integer lockVersion = 0;
	@Column(name = "entity_UID", unique = true, nullable = false, updatable = false, columnDefinition = "uniqueidentifier")
	protected String uid;
	@Transient
	private boolean shouldUpdate = true;
	@ManyToOne
	@JoinColumn(name = "created_by_id", nullable = true)
	protected DataAuthor createdBy;
	@ManyToOne
	@JoinColumn(name = "updated_by_id", nullable = true)
	protected DataAuthor updatedBy;

	public Entity()
	{
		super();
	}

	public Entity(Long id)
	{
		super();
		setId(id);
	}

	public String getXmlId()
	{
		return getId() != null ? getId().toString() : getUid();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof Entity))
		{
			return false;
		}
		Entity other = (Entity) o;
		if (other.getClass().isInstance(this))
		{
			if (getId() == null)
			{
				return getUid().equals(other.getUid());
			}
			else
			{
				return getId().equals(other.getId());
			}
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		if (getId() == null)
		{
			return getUid().hashCode();
		}
		else
		{
			return getId().hashCode();
		}
	}

	public static class UIDListener
	{
		@PrePersist
		public void onPrePersist(Entity entity)
		{
			entity.getUid();
		}
	}

	public String getUid()
	{
		if (uid == null)
			uid = UUID.randomUUID().toString();
		return uid;
	}

	public void clearIdentityFields()
	{
		setId(null);
		uid = null;
	}

	public abstract Long getId();

	public abstract void setId(Long id);

	public Date getCreated()
	{
		if (created == null)
		{
			created = new Date(System.currentTimeMillis());
		}
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public Integer getLockVersion()
	{
		return lockVersion;
	}

	public void setLockVersion(Integer lockVersion)
	{
		this.lockVersion = lockVersion;
	}

	public boolean isSameVersion(Entity entity)
	{
		return getLockVersion().equals(entity.getLockVersion());
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer(getClass().getName()).append("#");
		if (getId() != null)
		{
			buffer.append(getId());
		}
		else
		{
			buffer.append("temp-").append(getUid());
		}
		return buffer.toString();
	}

	public String getDisplayString()
	{
		return this.toString();
	}

	public boolean shouldUpdate()
	{
		return shouldUpdate;
	}

	public void setShouldUpdate(boolean shouldUpdate)
	{
		this.shouldUpdate = shouldUpdate;
	}

	public DataAuthor getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(DataAuthor createdBy)
	{
		this.createdBy = createdBy;
	}

	public DataAuthor getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(DataAuthor updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public void touch()
	{
		setUpdated(new Date(System.currentTimeMillis()));
	}
}
