package com.atguigu.hospital.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * HospitalSet
 * </p>
 *
 * @author qy
 */
@ApiModel(description = "HospitalSet")
@TableName("hospital_set")
@Data
public class HospitalSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	private String hoscode;

	@ApiModelProperty(value = "签名秘钥")
	private String signKey;

	@ApiModelProperty(value = "api基础路径")
	private String apiUrl;

	public String getHoscode() {
		return hoscode;
	}

	public void setHoscode(String hoscode) {
		this.hoscode = hoscode;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
}

