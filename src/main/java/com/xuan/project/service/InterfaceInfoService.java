package com.xuan.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuan.project.common.DeleteRequest;
import com.xuan.project.common.IdRequest;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.xuan.project.model.entity.InterfaceInfo;
import com.xuan.project.model.entity.User;

/**
* @author 炫
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-01-17 22:36:00
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo);

    /**
     * @description: 添加接口
     * @author: xuan
     * @date: 2023/1/18 13:32
     **/
    Boolean addInterface(InterfaceInfoAddRequest interfaceInfoAddRequest, User loginUser);

    /**
     * @description: 删除接口
     * @author: xuan
     * @date: 2023/1/19 0:17
     **/
    Boolean deleteInterface(DeleteRequest deleteRequest, User loginUser);

    /**
     * @description: 更新接口
     * @author: xuan
     * @date: 2023/1/19 0:32
     **/
    Boolean updateInterface(InterfaceInfoUpdateRequest interfaceInfoUpdateRequest, User loginUser);

    /**
     * @description: 分页获取接口信息
     * @author: xuan
     * @date: 2023/1/19 0:49
     **/
    Page<InterfaceInfo> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, User loginUser);

    /**
     * @description: 发布接口
     * @author: xuan
     * @date: 2023/1/20 0:34
     **/
    Boolean onlineInterfaceInfo(IdRequest idRequest);

    /**
     * @description: 下线接口
     * @author: xuan
     * @date: 2023/1/20 0:35
     **/
    Boolean offlineInterfaceInfo(IdRequest idRequest);
}
