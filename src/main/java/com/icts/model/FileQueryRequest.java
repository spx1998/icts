package com.icts.model;

import com.icts.model.request.base.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = true)
public class FileQueryRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 4623052236397927656L;
}
