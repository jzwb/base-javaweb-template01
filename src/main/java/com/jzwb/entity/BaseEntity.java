package com.jzwb.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jzwb.listener.EntityListener;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 基类
 */
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	//ID属性名称
	public static final String ID_PROPERTY_NAME = "id";
	//创建日期属性名称
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";
	//修改日期属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";

	/**
	 * 保存验证组
	 */
	public interface Save extends Default {
	}

	/**
	 * 更新验证组
	 */
	public interface Update extends Default {
	}

	//ID
	private Long id;
	//创建日期
	private Date createDate;
	//修改日期
	private Date modifyDate;

	/**
	 * 获取ID
	 *
	 * @return ID
	 */
	@JsonProperty
	@DocumentId
	@Id
	// MySQL/SQLServer: @GeneratedValue(strategy = GenerationType.AUTO)
	// Oracle: @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	public Long getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@DateBridge(resolution = Resolution.SECOND)
	@Column(nullable = false, updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@DateBridge(resolution = Resolution.SECOND)
	@Column(nullable = false)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 重写equals方法
	 *
	 * @param obj 对象
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!BaseEntity.class.isAssignableFrom(obj.getClass()))
			return false;
		BaseEntity other = (BaseEntity) obj;
		return getId() != null && getId().equals(other.getId());
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}