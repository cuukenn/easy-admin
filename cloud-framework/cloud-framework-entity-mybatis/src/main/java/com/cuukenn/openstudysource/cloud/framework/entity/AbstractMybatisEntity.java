package com.cuukenn.openstudysource.cloud.framework.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(of = "id")
@ToString
public abstract class AbstractMybatisEntity implements ILogicDeleteEntity {
    private static final long serialVersionUID = -5132345367955693237L;
    /**
     * 主键
     */
    @TableId(value = ID, type = IdType.AUTO)
    @ToString.Exclude
    private Long id;
    /**
     * 创建人
     */
    @TableField(value = CREATED_BY, fill = FieldFill.INSERT)
    private String createdBy;
    /**
     * 更新人
     */
    @TableField(value = LAST_MODIFIED_BY, fill = FieldFill.INSERT_UPDATE)
    private String lastModifiedBy;
    /**
     * 创建时间
     */
    @TableField(value = CREATED_TIME, fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField(value = LAST_MODIFIED_TIME, fill = FieldFill.INSERT_UPDATE)
    private Date lastModifiedTime;
    /**
     * 更新时间
     */
    @TableField(value = IS_DELETED)
    @TableLogic(value = "false", delval = "true")
    private Boolean logicDeleted;
}
