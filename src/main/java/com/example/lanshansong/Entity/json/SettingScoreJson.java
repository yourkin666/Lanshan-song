package com.example.lanshansong.Entity.json;

import lombok.Data;

/**
 * @author yourkin666
 * @date 2024/10/30/10:49
 * @description
 */
@Data
public class SettingScoreJson {
    // 通过
    ScoreJson successScore;
    // 人工审核
    ScoreJson manualScore;
    // PASS
    ScoreJson passScore;
}
