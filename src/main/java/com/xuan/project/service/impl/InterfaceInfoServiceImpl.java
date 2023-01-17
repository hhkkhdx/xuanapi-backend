package com.xuan.project.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuan.project.common.ErrorCode;
import com.xuan.project.exception.BusinessException;
import com.xuan.project.mapper.InterfaceInfoMapper;
import com.xuan.project.model.entity.InterfaceInfo;
import com.xuan.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 炫
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-01-17 22:36:00
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean b) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestParams = interfaceInfo.getRequestParams();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();

        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(name) || name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
        }
        if (StringUtils.isNotBlank(description) || description.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述过长");
        }
        if (StringUtils.isBlank(url)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(url) || url.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口url过长");
        }
    }
}




