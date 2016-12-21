package com.gas.common.base;

import com.gas.common.utils.IdGen;

import java.io.Serializable;

/**
 * Created by GC on 2016/12/21.
 */
public class BaseEntity  implements Serializable {

    private  String id;
    /**
     * 备注
     * */
    private String remarks;
    /**
     * 删除标识(0:正常，1：删除)
     * */
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void  preInsert(){
        setId(IdGen.getuuid());
    }
}
