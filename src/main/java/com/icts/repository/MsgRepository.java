package com.icts.repository;

import com.icts.mapper.MsgMapper;
import com.icts.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MsgRepository {
    @Autowired
    private MsgMapper msgMapper;

    public List<Msg> batchQuery(Integer size) {
        return msgMapper.selectMsgsBySize(size);
    }

    public Msg queryByCode(String code) {
        return msgMapper.selectMsgByCode(code);
    }

    public void insert(String code, String content, Boolean isDisplay) {
        msgMapper.insertMsg(buildMsg(code, code, isDisplay));
    }

    public void updateByCode(String code, String content, Boolean isDisplay) {
        msgMapper.updateMsg(buildMsg(code, code, isDisplay));
    }

    private Msg buildMsg(String code, String content, Boolean isDisplay) {
        Msg msg = new Msg();
        msg.setCode(code);
        msg.setContent(content);
        msg.setIsDisplay(isDisplay);
        return msg;
    }
}
