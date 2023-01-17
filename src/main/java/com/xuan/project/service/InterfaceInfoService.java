package com.xuan.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuan.project.model.entity.InterfaceInfo;

/**
* @author 炫
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-01-17 22:36:00
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean b);
}
