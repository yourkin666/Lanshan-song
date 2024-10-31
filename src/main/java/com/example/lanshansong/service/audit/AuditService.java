package com.example.lanshansong.service.audit;

/**
 * @description: 用于处理审核
 * @Author: Xhy
 * @CreateTime: 2023-10-29 14:39
 */
public interface AuditService<T,R> {

    /**
     *  审核规范
     * @param task
     * @return
     */
    R audit(T task);
}
