package com.example.lanshansong.Entity.video;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.lanshansong.Entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 * 分类,隐藏视频标签
 * @author xhy
 * @since 2023-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Type extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "分类名称不可为空")
    private String name;

    private String description;

    private String icon;

    private Boolean open;

    private String labelNames;

    private Integer sort;

    @TableField(exist = false)
    private Boolean used;

    public List<String> buildLabel(){
        return Arrays.asList(labelNames.split(","));
    }
}

