package com.example.coolq.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class JsonVO {

    private Boolean block;

    /**
     * 转为JSONObject
     *
     * @return JSONObject
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSON(this).toString());
    }

    public static JsonVO empty() {
        JsonVO jsonVO = new JsonVO();
        jsonVO.setBlock(false);
        return jsonVO;
    }

}
