package com.black.trade.core.bean;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockHoldContext {

    private Cash cash;

    private List<HoldStockInfo> holdInfo = new ArrayList<>();

    public List<HoldStockInfo> getStillHold() {
        return holdInfo.stream()
                .filter(hold -> !hold.getUnSaleList().isEmpty())
                .collect(Collectors.toList());
    }

    public class HoldStockInfo {

        @Getter
        private StockTimeInfo stockTimeInfo;

        private List<HoldPriceInfoInfo> priceInfoInfo;

        public List<HoldPriceInfoInfo> getUnSaleList() {
            return priceInfoInfo.stream()
                    .filter(price -> !price.isSale())
                    .collect(Collectors.toList());
        }

        public List<HoldPriceInfoInfo> getAllList() {
            return priceInfoInfo;
        }

        public List<HoldPriceInfoInfo> getSaledList() {
            return priceInfoInfo.stream()
                    .filter(HoldPriceInfoInfo::isSale)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public class HoldPriceInfoInfo {

        /**
         * 购买价格
         */
        private Price purchasePrice;

        /**
         * 出售价格
         */
        private Price sellingPrice;

        /**
         * 持有数量
         */
        private Integer count;

        public boolean isSale() {
            return Objects.isNull(sellingPrice);
        }
    }



}
