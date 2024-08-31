package com.icts.model.request;

import com.icts.model.request.base.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsgBatchQueryRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 916383591325779338L;
    private Integer size;
}
