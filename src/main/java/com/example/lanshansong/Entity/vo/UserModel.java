package com.example.lanshansong.Entity.vo;

import com.example.lanshansong.holder.UserHolder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-10-27 11:53
 */
@Data
public class UserModel {
    private List<Model> models;
    private Long userId;




    public static UserModel buildUserModel(List<String> labels,Long videoId,Double score){
        final UserModel userModel = new UserModel();
        final ArrayList<Model> models = new ArrayList<>();
        userModel.setUserId(UserHolder.get());
        for (String label : labels) {
            final Model model = new Model();
            model.setLabel(label);
            model.setScore(score);
            model.setVideoId(videoId);
            models.add(model);
        }
        userModel.setModels(models);
        return userModel;
    }

}