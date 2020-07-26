package org.yuekeju.common.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 公共继承
 * @author szk
 * 2020-7-5
 */
@SuppressWarnings("all")
@Data
public class YuekejuPersionLiableVO implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  创建人
	 */
	private String creater;
	/**
	 * 修改人
	 */
	private String modified;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
}
