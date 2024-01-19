package com.black.trade.access.converter;

import com.alibaba.fastjson.JSONObject;
import com.black.trade.access.model.dto.SpiderMateDataDto;
import com.black.trade.access.model.entity.PipeLineProperty;
import com.black.trade.access.model.entity.ProcessProperty;
import com.black.trade.access.DO.SpiderMateData;

/**
 * 转换器
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public class SpiderMateDataConverter {
    /**
     * entity convert to dto
     *
     * @param entity
     * @return
     */
    public static SpiderMateDataDto convertDto(SpiderMateData entity) {
        PipeLineProperty pipeLineProperty = JSONObject.parseObject(entity.getPipeLineProperty(), PipeLineProperty.class);
        ProcessProperty processProperty = JSONObject.parseObject(entity.getProcessProperty(), ProcessProperty.class);

        SpiderMateDataDto spiderMateDataDto = SpiderMateDataDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .spiderType(entity.getSpiderType())
                .spiderName(entity.getSpiderName())
                .spiderUrl(parseUrl(entity.getSpiderUrl()))
                .spiderStatus(entity.getSpiderStatus())
                .schedulerId(entity.getSchedulerId())
                .pipeLineProperty(pipeLineProperty)
                .processProperty(processProperty)
                .build();

        return spiderMateDataDto;
    }

    /**
     * 解析url
     *
     * @param sourceUrl
     * @return
     */
    private static String parseUrl(String sourceUrl) {
        long currentTimeMillis = System.currentTimeMillis();
        return sourceUrl.replaceAll("\\{currentTimeMillis}", String.valueOf(currentTimeMillis));
    }


}
