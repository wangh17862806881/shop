package com.fh.shop.api.area.biz;

import com.fh.shop.api.common.ServerResponse;

public interface IAreaService {
    ServerResponse findAllList();

    ServerResponse findAreaByid(Integer id);

    ServerResponse findClildren(Long id);
}
