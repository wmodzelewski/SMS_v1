package org.wmodzelewski.sts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licenses")
public class License {
	@Id
	@Column(name = "license_id")
	private String id;

	@Column(name = "organizationId")
	private String organizationId;

	@Column(name = "productName")
	private String productName;

	@Column(name = "comment")
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private String licenseType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public License withId(String id) {
		this.setId(id);
		return this;
	}

	public License withOrganizationId(String organizationId) {
		this.setOrganizationId(organizationId);
		return this;
	}

	public License withProductName(String productName) {
		this.setProductName(productName);
		return this;
	}

	public License withLicenseType(String licenseType) {
		this.setLicenseType(licenseType);
		return this;
	}

	public License withComment(String exampleProperty) {
		this.setComment("test4 " + exampleProperty);
		return this;
	}

}