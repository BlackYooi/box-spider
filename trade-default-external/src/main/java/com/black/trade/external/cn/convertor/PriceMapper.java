package com.black.trade.external.cn.convertor;

import com.black.trade.core.bean.Price;
import com.black.trade.external.cn.DO.KDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mappings({
            @Mapping(source = "s_date", target = "time", qualifiedByName = "endOfThisDay"),
            @Mapping(source = "s_close", target = "currentPrice"),
            @Mapping(source = "s_open", target = "open"),
            @Mapping(source = "s_close", target = "close"),
            @Mapping(source = "s_high", target = "high"),
            @Mapping(source = "s_low", target = "low"),
            @Mapping(source = "s_cjl", target = "cjl"),
            @Mapping(source = "s_cje", target = "cje"),
            @Mapping(source = "s_zf", target = "zf"),
            @Mapping(source = "s_zdf", target = "zdf"),
            @Mapping(source = "s_zde", target = "zde"),
            @Mapping(source = "s_hsl", target = "hsl")
    })
    Price kDOToPrice(KDO kdo);

    @Named("endOfThisDay")
    default LocalDateTime endOfThisDay(LocalDate localDate) {
        return LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59, 59);
    }
}
