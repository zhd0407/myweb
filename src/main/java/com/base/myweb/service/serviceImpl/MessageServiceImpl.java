package com.base.myweb.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.MessageMapper;
import com.base.myweb.pojo.Message;
import com.base.myweb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    public List<Message> getMessageByNoteNo(String noteNo){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("NOTE_NO",noteNo);
        return messageMapper.selectList(queryWrapper);


    }
}
