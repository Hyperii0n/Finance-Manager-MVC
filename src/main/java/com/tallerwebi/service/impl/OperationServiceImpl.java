package com.tallerwebi.service.impl;

import com.tallerwebi.model.Operation;
import com.tallerwebi.repository.OperationRepository;
import com.tallerwebi.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("operationService")
@Transactional
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {this.operationRepository = operationRepository;}

    @Override
    public List<Operation> getRecentOperationsByUserId(Long id) {
        return this.operationRepository.getRecentOperationsByUserId(id);
    }
}
