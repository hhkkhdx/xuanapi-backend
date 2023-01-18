package com.xuan.project.model.enums;

import java.util.Objects;


/**
 * @description: 接口状态枚举类
 * @author: xuan
 * @date: 2023/1/19 0:01
 **/
public enum InterfaceStatusEnum {

    CLOSE(0, "关闭"),
    OPEN(1, "开启");

    private final Integer value;

    private final String text;

    public static InterfaceStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        InterfaceStatusEnum[] values = InterfaceStatusEnum.values();
        for (InterfaceStatusEnum interfaceStatusEnum: values) {
            if (Objects.equals(interfaceStatusEnum.getValue(), value)) {
                return interfaceStatusEnum;
            }
        }
        return null;
    }

    InterfaceStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
