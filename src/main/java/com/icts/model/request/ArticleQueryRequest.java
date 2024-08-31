package com.icts.model.request;

import com.icts.model.request.base.BaseAuthRequest;
import com.icts.model.request.base.BaseRequest;
import com.sun.xml.internal.ws.api.model.SEIModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleQueryRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 7331600831811155847L;
    private String code;

}
