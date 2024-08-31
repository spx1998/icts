package com.icts.mapper;

import com.icts.model.Msg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgMapper {
    // 插入一条消息
    int insertMsg(Msg msg);

    // 根据 ID 查询消息
    Msg selectMsgById(Integer id);

    // 根据 code 查询消息
    Msg selectMsgByCode(String code);

    // 更新消息内容（包括新增的字段）
    int updateMsg(Msg msg);

    // 删除消息
    int deleteMsg(Integer id);

    // 根据 code 删除消息
    int deleteMsgByCode(String code);

    // 查询所有消息
    List<Msg> selectAllMsgs();

    // 根据 size 查询指定大小的列表
    List<Msg> selectMsgsBySize(int size);
}