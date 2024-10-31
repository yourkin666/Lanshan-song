package com.example.lanshansong.Entity.response;

import com.example.lanshansong.Entity.task.VideoTask;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 封装视频审核返回结果
 * @Author: Xhy
 * @CreateTime: 2023-10-29 14:40
 */
@Data
@ToString
public class VideoAuditResponse {

    private AuditResponse videoAuditResponse;

    private AuditResponse imageAuditResponse;

    private AuditResponse textAuditResponse;

    private VideoTask videoTask;
}
