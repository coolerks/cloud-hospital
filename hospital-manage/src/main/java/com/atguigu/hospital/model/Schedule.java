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
 * Schedule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Schedule")
@TableName("schedule")
public class Schedule extends BaseNoAutoEntity {

	private static final long serialVersionUID = 1L;

	public String getHoscode() {
		return hoscode;
	}

	public void setHoscode(String hoscode) {
		this.hoscode = hoscode;
	}

	public String getDepcode() {
		return depcode;
	}

	public void setDepcode(String depcode) {
		this.depcode = depcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getReservedNumber() {
		return reservedNumber;
	}

	public void setReservedNumber(Integer reservedNumber) {
		this.reservedNumber = reservedNumber;
	}

	public Integer getAvailableNumber() {
		return availableNumber;
	}

	public void setAvailableNumber(Integer availableNumber) {
		this.availableNumber = availableNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ApiModelProperty(value = "医院编号")
	@TableField("hoscode")
	private String hoscode;

	@ApiModelProperty(value = "科室编号")
	@TableField("depcode")
	private String depcode;

	@ApiModelProperty(value = "职称")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "医生名称")
	@TableField("docname")
	private String docname;

	@ApiModelProperty(value = "擅长技能")
	@TableField("skill")
	private String skill;

	@ApiModelProperty(value = "安排日期")
	@TableField("work_date")
	private String workDate;

	@ApiModelProperty(value = "安排时间（0：上午 1：下午）")
	@TableField("work_time")
	private Integer workTime;

	@ApiModelProperty(value = "可预约数")
	@TableField("reserved_number")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩余预约数")
	@TableField("available_number")
	private Integer availableNumber;

	@ApiModelProperty(value = "挂号费")
	@TableField("amount")
	private String amount;

	@ApiModelProperty(value = "排班状态（-1：停诊 0：停约 1：可约）")
	@TableField("status")
	private Integer status;
}

