package com.atguigu.hospital.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * Patient
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Patient")
@TableName("patient")
public class Patient extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertificatesType() {
		return certificatesType;
	}

	public void setCertificatesType(String certificatesType) {
		this.certificatesType = certificatesType;
	}

	public String getCertificatesNo() {
		return certificatesNo;
	}

	public void setCertificatesNo(String certificatesNo) {
		this.certificatesNo = certificatesNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(Integer isMarry) {
		this.isMarry = isMarry;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsCertificatesType() {
		return contactsCertificatesType;
	}

	public void setContactsCertificatesType(String contactsCertificatesType) {
		this.contactsCertificatesType = contactsCertificatesType;
	}

	public String getContactsCertificatesNo() {
		return contactsCertificatesNo;
	}

	public void setContactsCertificatesNo(String contactsCertificatesNo) {
		this.contactsCertificatesNo = contactsCertificatesNo;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "证件类型")
	@TableField("certificates_type")
	private String certificatesType;

	@ApiModelProperty(value = "证件编号")
	@TableField("certificates_no")
	private String certificatesNo;

	@ApiModelProperty(value = "性别")
	@TableField("sex")
	private Integer sex;

	@ApiModelProperty(value = "出生年月")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("birthdate")
	private String birthdate;

	@ApiModelProperty(value = "手机")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "是否结婚")
	@TableField("is_marry")
	private Integer isMarry;

	@ApiModelProperty(value = "省code")
	@TableField("province_code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	@TableField("city_code")
	private String cityCode;

	@ApiModelProperty(value = "区code")
	@TableField("district_code")
	private String districtCode;

	@ApiModelProperty(value = "详情地址")
	@TableField("address")
	private String address;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("contacts_name")
	private String contactsName;

	@ApiModelProperty(value = "联系人证件类型")
	@TableField("contacts_certificates_type")
	private String contactsCertificatesType;

	@ApiModelProperty(value = "联系人证件号")
	@TableField("contacts_certificates_no")
	private String contactsCertificatesNo;

	@ApiModelProperty(value = "联系人手机")
	@TableField("contacts_phone")
	private String contactsPhone;

}

