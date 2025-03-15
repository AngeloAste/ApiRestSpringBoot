package com.challengercode.spring.apirest.service;
import com.challengercode.spring.apirest.model.CallHistory;
import com.challengercode.spring.apirest.repository.CallHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CallHistoryService {

    @Autowired
    private CallHistoryRepository repository;

    @Async
    public void saveCallAsync(CallHistory call) {
        repository.save(call);
    }

    public CallHistory saveCall(CallHistory call) {
        return repository.save(call);
    }

    // ✅ CORRECCIÓN: Ahora devuelve una lista en lugar de una página
    public List<CallHistory> getCallHistory() {
        return repository.findAll();
    }
}