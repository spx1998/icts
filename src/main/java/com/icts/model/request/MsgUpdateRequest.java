package com.icts.model.request;

import com.icts.model.request.base.BaseAuthRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsgUpdateRequest extends BaseAuthRequest implements Serializable {

    private static final long serialVersionUID = 2870259895536423755L;
}
