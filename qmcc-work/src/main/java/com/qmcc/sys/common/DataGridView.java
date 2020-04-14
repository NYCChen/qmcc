package com.qmcc.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json实体数据
 * 为了在后台管理也买你左侧的菜单栏数据显示
 */
@Data
@AllArgsConstructor
public class DataGridView {

    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
        this.data = data;
    }

    public DataGridView(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
