package org.yuekeju.common.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * app轮播图
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_app_banner")
@EqualsAndHashCode(callSuper = true)
@Data
public class YuekejuAppBannerEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
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
     * 逻辑删除注解
     * 新增的时候自动插入，MetaObjectHandlerConfig配置文件
     */
    @TableLogic  
    @TableField(value="del_tab_status",fill = FieldFill.INSERT)  
    private BigDecimal delTabStatus;

}
