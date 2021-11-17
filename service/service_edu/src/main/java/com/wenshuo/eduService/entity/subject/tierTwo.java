package com.wenshuo.eduService.entity.subject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class tierTwo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;
}
