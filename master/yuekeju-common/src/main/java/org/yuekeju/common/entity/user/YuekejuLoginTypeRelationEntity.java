package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
public class YuekejuLoginTypeRelationEntity implements Serializable {
    private static final long serialVersionUID = -6935709135793179159L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long typeId;

}
