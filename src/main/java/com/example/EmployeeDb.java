package com.example;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmployeeDb {

    @PersistenceContext
    private EntityManager em;

    public void create(Employee employee) {
        em.persist(employee);
    }

    public void update(Employee employee) {
        em.merge(employee);
    }

    public void delete(Employee employee) {
        em.remove(em.merge(employee));
    }

    public Employee find(Integer key) {
        return em.find(Employee.class, key);
    }

    public List<Employee> getAll(int pageNumber) {
        int pageSize = 5;
        Query query = em.createQuery("SELECT c FROM Employee c");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
