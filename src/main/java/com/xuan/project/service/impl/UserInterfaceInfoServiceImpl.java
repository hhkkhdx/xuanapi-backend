package com.xuan.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuan.project.model.entity.UserInterfaceInfo;
import com.xuan.project.service.UserInterfaceInfoService;
import com.xuan.project.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 炫
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-01-27 13:52:58
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




