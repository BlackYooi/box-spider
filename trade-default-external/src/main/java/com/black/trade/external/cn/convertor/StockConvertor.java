package com.black.trade.external.cn.convertor;

import com.black.trade.core.bean.Price;
import com.black.trade.core.bean.StockInfo;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.external.cn.DO.KDO;

import java.util.*;
import java.util.stream.Collectors;

public class StockConvertor {

    public static List<StockTimeInfo> toTimeInfo(List<KDO> list) {
        if (Objects.isNull(list)) {
            return new ArrayList<>();
        }
        Map<String, List<KDO>> groupByCode = list.stream().collect(Collectors.groupingBy(KDO::getS_code));
        List<StockTimeInfo> infos = groupByCode.entrySet()
                .stream()
                .map((entry) -> {
                    List<Price> prices = entry.getValue()
                            .stream()
                            .map(PriceMapper.INSTANCE::kDOToPrice)
                            .sorted(Comparator.comparing(Price::getTime))
                            .collect(Collectors.toList());
                    return new StockTimeInfo(new StockInfo(entry.getKey()), prices);
                })
                .collect(Collectors.toList());
        return infos;
    }
}
