package com.tallerwebi.service;

import com.tallerwebi.model.Operation;

import java.util.List;

public interface OperationService {

    List<Operation> getRecentOperationsByUserId(Long id);

}
