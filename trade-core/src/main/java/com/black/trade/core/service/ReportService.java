package com.black.trade.core.service;

import com.black.trade.core.bean.StockHoldContext;
import com.black.trade.core.bean.TimeConText;

public interface ReportService {

    void report(TimeConText timeConText, StockHoldContext stockHoldContext);
}
