package com.example.web.service;

import com.example.web.dao.HistDao;
import com.example.web.domain.HistWifi;

import java.util.List;

public class HistService {

    /**
     * DB에 저장된 검색 내역 리스트 불러오기
     * @return Array
     */
    public List<HistWifi> list() {
        HistDao histDao = new HistDao();
        return histDao.selectAll();
    }

    public void save(HistWifi histWifi) {
        HistDao histDao = new HistDao();
        histDao.insert(histWifi);
    }

}
