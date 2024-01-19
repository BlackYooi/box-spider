package com.black.trade.access.dao;

import com.black.trade.access.constant.enums.SpiderStatusEnum;
import com.black.trade.access.constant.enums.SpiderTypeEnum;
import com.black.trade.access.dao.mapper.SpiderMateDataMapper;
import com.black.trade.access.DO.SpiderMateData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 爬虫元数据DAO
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpiderMateDataDao {

    private final SpiderMateDataMapper spiderMateDataMapper;

    public List<SpiderMateData> querySpiderMateData(SpiderTypeEnum spiderTypeEnum) {
        Example example = new Example(SpiderMateData.class);
        example.createCriteria()
                .andEqualTo("spiderType", spiderTypeEnum)
                .andEqualTo("spiderStatus", SpiderStatusEnum.ON.getValue());

        return spiderMateDataMapper.selectByExample(example);
    }
}
