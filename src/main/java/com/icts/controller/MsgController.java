package com.icts.controller;

import com.alibaba.fastjson2.JSON;
import com.icts.model.Article;
import com.icts.model.Msg;
import com.icts.model.Response;
import com.icts.model.request.MsgBatchQueryRequest;
import com.icts.model.request.MsgUpdateRequest;
import com.icts.repository.ArticleRepository;
import com.icts.repository.MsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController("/msg")
@Slf4j
public class MsgController {

    @Autowired
    private MsgRepository msgRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public Response<Article> query(MsgBatchQueryRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getSize())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        List<Msg> msgList = msgRepository.batchQuery(request.getSize());
//        if () {
//            return Response.failed("article not exist");
//        }
//        return Response.success(article);
        return Response.failed(";");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Response<String> update(MsgUpdateRequest request) {
//        Article article = msgRepository.queryByCode(code);
//        if (Objects.isNull(article)) {
//            return Response.failed("article not exist");
//        }
//        msgRepository.updateByCode(code);
//        return Response.success(code);
        throw new RuntimeException();
    }
}
