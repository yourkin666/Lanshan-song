package com.example.lanshansong.Entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.lanshansong.Entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author yourkin666
 * @date 2024/10/29/13:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Favorites extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "得给你的收藏夹取个名字吧?")
    private String name;

    private String description;

    private Long userId;

    // 收藏夹下的视频总数
    @TableField(exist = false)
    private Long videoCount;
}
