package com.fh.shop.api.common;

import java.io.Serializable;

public class page  implements Serializable {

    //起始下标
    private Integer start;
    //每页显示条数
    private Integer length;
    //第几页
    private Integer draw;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }
}
