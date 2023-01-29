package com.atguigu.hospital.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * OrderInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "OrderInfo")
@TableName("order_info")
public class OrderInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(String fetchTime) {
		this.fetchTime = fetchTime;
	}

	public String getFetchAddress() {
		return fetchAddress;
	}

	public void setFetchAddress(String fetchAddress) {
		this.fetchAddress = fetchAddress;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@ApiModelProperty(value = "排班id")
	@TableField("schedule_id")
	private Long scheduleId;

	@ApiModelProperty(value = "就诊人id")
	@TableField("patient_id")
	private Long patientId;

	@ApiModelProperty(value = "预约号序")
	@TableField("number")
	private Integer number;

	@ApiModelProperty(value = "建议取号时间")
	@TableField("fetch_time")
	private String fetchTime;

	@ApiModelProperty(value = "取号地点")
	@TableField("fetch_address")
	private String fetchAddress;

	@ApiModelProperty(value = "医事服务费")
	@TableField("amount")
	private BigDecimal amount;

	@ApiModelProperty(value = "支付时间")
	@TableField("pay_time")
	private Date payTime;

	@ApiModelProperty(value = "退号时间")
	@TableField("quit_time")
	private Date quitTime;

	@ApiModelProperty(value = "订单状态")
	@TableField("order_status")
	private Integer orderStatus;

}

