package com.tallerwebi.repository;

import com.tallerwebi.model.Operation;

import java.util.List;

public interface OperationRepository {

    List<Operation> getRecentOperationsByUserId(Long id);

}
