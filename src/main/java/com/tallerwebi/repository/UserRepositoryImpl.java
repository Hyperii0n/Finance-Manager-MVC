package com.tallerwebi.repository;

import com.tallerwebi.service.interfaces.UserRepository;
import com.tallerwebi.service.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User findByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modify(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public String findPhotoByEmail(String email) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u.photoUrl FROM User u WHERE u.email = :email", String.class)
                .setParameter("email", email)
                .getSingleResult().toString();
    }

}
