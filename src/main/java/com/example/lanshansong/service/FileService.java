package com.example.lanshansong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lanshansong.Entity.File;

/**
 * @author yourkin666
 * @date 2024/10/30/10:40
 * @description
 */
public interface FileService extends IService<File> {


    Long save(String fileKey,Long userId);

    /**
     * 根据视频id生成图片
     * @param fileId
     * @return
     */
    Long generatePhoto(Long fileId,Long userId);

    /**
     * 获取文件真实URL
     * @param fileId 文件id
     * @return
     */
    File getFileTrustUrl(Long fileId);
}
