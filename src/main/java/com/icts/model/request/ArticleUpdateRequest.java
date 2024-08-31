package com.icts.model.request;

import com.icts.model.request.base.BaseAuthRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleUpdateRequest extends BaseAuthRequest implements Serializable {
    private static final long serialVersionUID = -2052605595675180278L;
    private String code;
    private String title;
    private String content;
}
