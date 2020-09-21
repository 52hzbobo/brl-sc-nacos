package com.brl.sc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeleteEnum {
    UN_DELETE(0,"未删除"),
    DELETED(-1,"已删除"),
    ;
    @Getter
    private Integer type ;
    @Getter
    private String desc ;

    public static DeleteEnum getByType(Integer type){
        for (DeleteEnum em : DeleteEnum.values()){
            if(em.getType()==type){
                return em ;
            }
        }
        return null ;
    }
}
