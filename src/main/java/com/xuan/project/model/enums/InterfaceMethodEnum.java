package com.xuan.project.model.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @description: 接口方法枚举类
 * @author: xuan
 * @date: 2023/1/19 0:01
 **/
public enum InterfaceMethodEnum {

    GET(0, "GET"),
    POST(1, "POST"),
    PUT(2, "PUT"),
    DELETE(3, "DELETE");

    private final Integer value;

    private final String text;

    public static InterfaceMethodEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        InterfaceMethodEnum[] values = InterfaceMethodEnum.values();
        for (InterfaceMethodEnum interfaceMethodEnum: values) {
            if (Objects.equals(interfaceMethodEnum.getValue(), value)) {
                return interfaceMethodEnum;
            }
        }
        return null;
    }

    public static InterfaceMethodEnum getEnumByText(String text) {
        if (text == null) {
            return null;
        }
        InterfaceMethodEnum[] values = InterfaceMethodEnum.values();
        for (InterfaceMethodEnum interfaceMethodEnum: values) {
            if (Objects.equals(interfaceMethodEnum.getText(), text)) {
                return interfaceMethodEnum;
            }
        }
        return null;
    }

    InterfaceMethodEnum(Integer value, String text) {
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
