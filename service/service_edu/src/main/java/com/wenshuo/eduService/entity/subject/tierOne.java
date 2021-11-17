package com.wenshuo.eduService.entity.subject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class tierOne {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;
    private List<tierTwo> children = new ArrayList<>();
}
