package com.example.lanshansong.service.video;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lanshansong.Entity.video.Type;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhy
 * @since 2023-10-24
 */
public interface TypeService extends IService<Type> {

    /**
     * 获取分类下的标签
     * @param typeId
     * @return
     */
    List<String> getLabels(Long typeId);

    /**
     * 随机获取标签
     * @return
     */
    List<String> random10Labels();
}
