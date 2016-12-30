package com.gas.entity;


import com.gas.common.base.BaseEntity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by GC on 2016/12/27.
 */
public class SettingPrice extends BaseEntity {

    @NotNull(message = "价格不能为空！")
    @DecimalMin(value = "0.01",message = "价格必须大于0！")
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
