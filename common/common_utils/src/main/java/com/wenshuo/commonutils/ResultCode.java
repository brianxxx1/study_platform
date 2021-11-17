package com.wenshuo.commonutils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(20000),FAIL(20001);
    private final Integer num;

}
