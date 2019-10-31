package com.fh.shop.api.common;

import java.io.Serializable;
import java.util.List;

public class DataTableResult implements Serializable {

    private Long recordsFiltered;

    private Long recordsTotal;
    //第几页
    private Integer draw;

    private List data;

    public DataTableResult() {
    }

    public DataTableResult(Long recordsFiltered, Long recordsTotal, Integer draw, List data) {
        this.recordsFiltered = recordsFiltered;
        this.recordsTotal = recordsTotal;
        this.draw = draw;
        this.data = data;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
