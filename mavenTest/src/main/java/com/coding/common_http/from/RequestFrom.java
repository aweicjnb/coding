package com.coding.common_http.from;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@ToString
public class RequestFrom {

    //作用在Character：至少包含一个非空白字符
    @NotBlank(message = "活动名称不能为空")
    //作用在Character,Collection,Map,不为空，并且元素不为空
    @NotEmpty
    private String name = "大熊";
    @NotEmpty(message = "列表不能为空")
    private List<Integer> list;

    //作用在普通对象上,元素不为空
    @NotNull(message = "哈哈，错误了🙅")
    private Integer age;
    @NotNull
    @Valid
    private SubRequest subRequest;
}
