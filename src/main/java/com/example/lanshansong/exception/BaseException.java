package com.example.lanshansong.exception;

import lombok.Data;

/**
 * @author yourkin666
 * @date 2024/10/29/13:15
 * @description
 */
@Data
public class BaseException extends RuntimeException {

    String msg;

    public BaseException(String msg){
        this.msg = msg;
    }

}