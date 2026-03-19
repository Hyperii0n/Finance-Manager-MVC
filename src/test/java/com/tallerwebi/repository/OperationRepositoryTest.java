package com.tallerwebi.repository;

import com.tallerwebi.integration.HibernateTestConfig;
import com.tallerwebi.integration.SpringWebTestConfig;
import com.tallerwebi.model.Operation;
import com.tallerwebi.model.User;
import com.tallerwebi.model.enums.OperationType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
@Transactional
public class OperationRepositoryTest {

    @Autowired
    private  OperationRepository operationRepository;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void givenExistingUserHasSixOperations_whenGetRecentOperationsByUserId_thenReturnFiveSizeList(){
        User user1 = new User("alice@email.com","test","Alice","x");
        this.sessionFactory.getCurrentSession().save(user1);

        for(int i = 0; i < 7; i++){
            Operation operation = new Operation(BigDecimal.valueOf(50.0),"Food", LocalDateTime.now(), OperationType.INCOME);
            operation.setUser(user1);
            this.sessionFactory.getCurrentSession().save(operation);
        }

        List<Operation> recentOperations = this.operationRepository.getRecentOperationsByUserId(user1.getId());

        int lengthExpected = 5;
        assertThat(recentOperations.size(), equalTo(lengthExpected));

    }


}
