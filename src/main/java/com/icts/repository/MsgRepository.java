package com.icts.repository;

import com.icts.model.Msg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MsgRepository {
    public List<Msg> batchQuery(Integer size) {
        throw new RuntimeException();
    }
}
