package com.example.lanshansong.Entity.json;

import lombok.Data;

/**
 * @author yourkin666
 * @date 2024/10/30/10:50
 * @description
 */
@Data
public class ScoreJson{
    Double minPulp;
    Double maxPulp;

    Double minTerror;
    Double maxTerror;

    Double minPolitician;
    Double maxPolitician;

    Integer auditStatus;

}