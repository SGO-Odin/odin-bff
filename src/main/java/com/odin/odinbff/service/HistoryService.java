package com.odin.odinbff.service;

import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(@Autowired final HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Boolean save(final History history) {
        if(history.isValid()) {
            historyRepository.save(history);
            return true;
        }
        return false;
    }
}
