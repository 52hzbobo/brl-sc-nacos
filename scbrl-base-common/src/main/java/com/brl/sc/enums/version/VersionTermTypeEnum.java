package com.brl.sc.enums.version;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 终端类型
 */
@AllArgsConstructor
public enum VersionTermTypeEnum {
    ANDROID(1,"安卓"),
    IOS(2,"苹果"),
    ;

    @Getter
    private Integer type ;

    @Getter
    private String desc;


    public static VersionTermTypeEnum getByType(Integer type) {
        VersionTermTypeEnum[] enums = values();
        for (VersionTermTypeEnum theEnum : enums) {
            if(theEnum.getType()==type){
                return theEnum;
            }
        }
        return null;
    }

}
