package com.qmcc.bus.service;

import com.qmcc.bus.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author nyc
 * @since 2020-04-03
 */
public interface OutportService extends IService<Outport> {
    /**
     * 退货
     * @param id  进货单ID
     * @param number  退货数量
     * @param remark  备注
     */
    void addOutPort(Integer id, Integer number, String remark);
}
