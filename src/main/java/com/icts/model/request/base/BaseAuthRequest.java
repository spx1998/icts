package com.icts.model.request.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseAuthRequest extends BaseRequest {
    private String secret;
}
