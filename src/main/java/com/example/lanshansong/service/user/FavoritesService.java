package com.example.lanshansong.service.user;

/**
 * @author yourkin666
 * @date 2024/10/29/13:55
 * @description
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lanshansong.Entity.user.Favorites;

import java.util.List;

public interface FavoritesService extends IService<Favorites> {
    /**
     * 删除收藏夹,连收藏夹下的视频一块删除
     * @param id
     * @param userId
     */
    void remove(Long id, Long userId);

    /**
     * 根据用户获取收藏夹
     * @param userId
     * @return
     */
    List<Favorites> listByUserId(Long userId);

    /**
     * 获取收藏夹下的所有视频id
     * @param favoritesId
     * @param userId
     * @return
     */
    List<Long> listVideoIds(Long favoritesId,Long userId);

    /**
     * 收藏视频
     * @param fId
     * @param vId
     */
    boolean favorites(Long fId, Long vId);

    /**
     * 收藏状态
     * @param videoId
     * @param userId
     * @return
     */
    Boolean favoritesState(Long videoId, Long userId);

    void exist(Long userId, Long defaultFavoritesId);
}
