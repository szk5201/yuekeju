package org.yuekeju.common.vo;

import java.util.Date;

import lombok.Data;
/**
 * 公共继承
 * @author szk
 * 2020-7-5
 */
@Data
public class YuekejuPersionLiableVO {
	// 创建人
	private String creater;
	//修改人
	private String modified;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
}
