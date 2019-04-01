package sun.bz1.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 接单区域表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class Token {

    //接口访问凭证
    private String accessToken;
    //接口有效期，单位：秒
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}