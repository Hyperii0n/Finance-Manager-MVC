package com.tallerwebi.repository.impl;

import com.tallerwebi.model.Operation;
import com.tallerwebi.model.User;
import com.tallerwebi.repository.OperationRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("operationRepository")
public class OperationRepositoryImpl implements OperationRepository {

    private  SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Operation> getRecentOperationsByUserId(Long id) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Operation o WHERE o.user.id = :id ORDER BY o.date DESC", Operation.class)
                .setParameter("id", id)
                .setMaxResults(5)
                .list();
    }
}
