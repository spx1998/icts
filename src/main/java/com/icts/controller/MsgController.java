package com.icts.controller;

import com.alibaba.fastjson2.JSON;
import com.icts.model.Msg;
import com.icts.model.Response;
import com.icts.model.request.MsgBatchQueryRequest;
import com.icts.model.request.MsgUpdateRequest;
import com.icts.repository.MsgRepository;
import com.icts.utils.SecretUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/msg")
@Slf4j
public class MsgController {

    @Autowired
    private MsgRepository msgRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public Response<List<Msg>> query(MsgBatchQueryRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getSize())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        List<Msg> msgList = msgRepository.batchQuery(request.getSize());
        return Response.success(msgList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Response<String> update(@RequestBody MsgUpdateRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getCode()) || StringUtils.isBlank(request.getContent()) || Objects.isNull(request.getIsDisplay())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        if (!SecretUtils.check(request.getSecret())) {
            return Response.failed(String.format("wrong secret:%s", request.getSecret()));
        }
        Msg msg = msgRepository.queryByCode(request.getCode());
        if (Objects.isNull(msg)) {
            log.warn("insert msg:{}", request.getContent());
            msgRepository.insert(request.getCode(), request.getContent(),request.getIsDisplay());
            return Response.success("insert");

        } else {
            msgRepository.updateByCode(request.getCode(), request.getContent(),request.getIsDisplay());
        }
        return Response.success("update");
    }
}
