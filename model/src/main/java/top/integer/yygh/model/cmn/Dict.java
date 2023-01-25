package top.integer.yygh.model.cmn;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "数据字典")
@TableName("dict")
public class Dict {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @ExcelProperty(value = "编号")
    @TableId(type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @ExcelProperty("更新时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableLogic
    @TableField("is_deleted")
    @ExcelIgnore
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    @ExcelIgnore
    private Map<String,Object> param = new HashMap<>();

    @ApiModelProperty(value = "上级id")
    @TableField("parent_id")
    @ExcelProperty("上级id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    @ExcelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "值")
    @TableField("value")
    @ExcelProperty("值")
    private String value;

    @ApiModelProperty(value = "编码")
    @TableField("dict_code")
    @ExcelProperty("编码")
    private String dictCode;

    @ApiModelProperty(value = "是否包含子节点")
//    @TableField(exist = false)
    @ExcelProperty("是否包含子节点")
    private boolean hasChildren;

}
