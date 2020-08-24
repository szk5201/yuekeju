package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
public class YuekejuLoginTypeEntity implements Serializable {
    private static final long serialVersionUID = -4900742549686371660L;
    @TableId(type = IdType.AUTO)
    private int id;
    private String typeCode;
    private String typeName;
}
