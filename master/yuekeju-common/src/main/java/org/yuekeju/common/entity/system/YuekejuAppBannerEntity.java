package org.yuekeju.common.entity.system;

import java.math.BigDecimal;
import java.util.Date;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * app轮播图
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_app_banner")
@Data
public class YuekejuAppBannerEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 唯一标识
     */
    @TableField("yuekeju_code")
    private String yuekejuCode;
    /**
     * 主题
     */
    private String title;
    /**
     * 主题地址
     */
    private String url;
    /**
     * 图片地址
     */
    @TableField("image_url")
    private String imageUrl;
    /**
     * 状态（0：待上线  1上线  2：下线）
     */
    private BigDecimal status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标记
     */
    @TableField("del_tab_status")
    private BigDecimal delTabStatus;

}
