package org.yuekeju.common.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
	)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
	)
	private Date updateTime;
}
