package org.yuekeju.common.vo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
/**
 * 公共继承
 * @author szk
 * 2020-7-5
 */
@SuppressWarnings("all")
@Data
public abstract class YuekejuPersionLiableVO implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private String creater;
	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String modified;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
}
