package com.example.lanshansong.Entity.vo;

import lombok.Data;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-10-27 11:56
 */
@Data
public class Model {
    private String label;
    private Long videoId;
    /**
     * 暴漏的接口只有根据停留时长 {@link org.luckyjourney.controller.CustomerController#updateUserModel}
     */

    private Double score;
}
