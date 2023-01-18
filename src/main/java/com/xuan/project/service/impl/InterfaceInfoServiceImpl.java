package com.xuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuan.project.common.DeleteRequest;
import com.xuan.project.common.ErrorCode;
import com.xuan.project.constant.CommonConstant;
import com.xuan.project.exception.BusinessException;
import com.xuan.project.mapper.InterfaceInfoMapper;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.xuan.project.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.xuan.project.model.entity.InterfaceInfo;
import com.xuan.project.model.entity.User;
import com.xuan.project.model.enums.InterfaceMethodEnum;
import com.xuan.project.model.enums.InterfaceStatusEnum;
import com.xuan.project.service.InterfaceInfoService;
import com.xuan.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author 炫
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-01-17 22:36:00
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {


    @Autowired
    private UserService userService;


    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id错误");
        }
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称不能为空");
        }
        if (StringUtils.isNotBlank(name) || name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
        }
        if (StringUtils.isNotBlank(description) || description.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述过长");
        }
        if (StringUtils.isBlank(url)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "url地址不能为空");
        }
        if (StringUtils.isNotBlank(url) || url.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口url过长");
        }
        InterfaceStatusEnum interfaceStatusEnum = InterfaceStatusEnum.getEnumByValue(status);
        if (interfaceStatusEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口状态错误");
        }
        InterfaceMethodEnum interfaceMethodEnum = InterfaceMethodEnum.getEnumByText(method);
        if (interfaceMethodEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口方法错误");
        }
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "userId错误");
        }
    }


    @Override
    public Boolean addInterface(InterfaceInfoAddRequest interfaceInfoAddRequest, User loginUser) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        this.validInterfaceInfo(interfaceInfo);
        return this.save(interfaceInfo);
    }

    @Override
    public Boolean deleteInterface(DeleteRequest deleteRequest, User loginUser) {
        if (deleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Long deleteId = deleteRequest.getId();
        if (deleteId == null || deleteId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean isAdmin = userService.isAdmin(loginUser);
        InterfaceInfo interfaceInfo = this.getById(deleteId);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Long interfaceInfoUserId = interfaceInfo.getUserId();
        if (!loginUser.getId().equals(interfaceInfoUserId) || !isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return this.removeById(deleteId);
    }

    @Override
    public Boolean updateInterface(InterfaceInfoUpdateRequest interfaceInfoUpdateRequest, User loginUser) {
        if (interfaceInfoUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Long updateId = interfaceInfoUpdateRequest.getId();
        if (updateId == null || updateId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口id错误");
        }
        InterfaceInfo oldInterfaceInfo = this.getById(updateId);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Long loginUserId = loginUser.getId();
        boolean isAdmin = userService.isAdmin(loginUser);
        if (!Objects.equals(oldInterfaceInfo.getUserId(), loginUserId) || !isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        InterfaceInfo newInterfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, newInterfaceInfo);
        this.validInterfaceInfo(newInterfaceInfo);
        return this.updateById(newInterfaceInfo);
    }

    @Override
    public Page<InterfaceInfo> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, User loginUser) {
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if (interfaceInfoQueryRequest == null) {
            return this.page(new Page<>(1, 20), null);
        }
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return this.page(new Page<>(current, size), queryWrapper);
    }


}




