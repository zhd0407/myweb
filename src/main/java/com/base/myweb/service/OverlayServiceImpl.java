package com.base.myweb.service;

import com.base.myweb.mapper.OverlayMapper;
import com.base.myweb.pojo.Overlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverlayServiceImpl  {

    @Autowired
    OverlayMapper overlayMapper;

    public void insertOverlay(Overlay overlay){
        overlayMapper.insert(overlay);
    }

    public List<Overlay> getOverlayInfo(){
        return overlayMapper.selectList(null);
    }
}

