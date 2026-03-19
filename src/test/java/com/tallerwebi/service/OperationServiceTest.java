package com.tallerwebi.service;

import com.tallerwebi.model.Operation;
import com.tallerwebi.model.User;
import com.tallerwebi.model.enums.OperationType;
import com.tallerwebi.repository.OperationRepository;
import com.tallerwebi.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class OperationServiceTest {

    private OperationService operationService;
    private OperationRepository operationRepository;

    @BeforeEach
    public void setUp() {
        operationRepository = mock(OperationRepository.class);
        operationService =  new OperationServiceImpl(operationRepository);
    }

    @Test
    public void givenExistingUserWithOperations_whenGetRecentOperationsByUserId_thenReturnList() {
        User user1 = new User("alice@email.com","test","Alice","x");

        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation());
        operations.add(new Operation());

        when(this.operationRepository.getRecentOperationsByUserId(user1.getId())).thenReturn(operations);

        List<Operation> recentOperations =  operationService.getRecentOperationsByUserId(user1.getId());

        verify(this.operationRepository).getRecentOperationsByUserId(user1.getId());
        assertThat(recentOperations, equalTo(operations));
    }

    @Test
    public void givenExistingUserWithNoOperations_whenGetRecentOperationsByUserId_thenEmptyList(){
        User user1 = new User("alice@email.com","test","Alice","x");

        List<Operation> operations = new ArrayList<>();
        when(this.operationRepository.getRecentOperationsByUserId(user1.getId())).thenReturn(operations);

        List<Operation> recentOperations =  operationService.getRecentOperationsByUserId(user1.getId());

        verify(this.operationRepository).getRecentOperationsByUserId(user1.getId());
        assertThat(recentOperations, equalTo(operations));
        assertThat(recentOperations.size(), equalTo(0));
    }
}
