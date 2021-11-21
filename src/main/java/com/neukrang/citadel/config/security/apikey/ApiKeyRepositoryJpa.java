package com.neukrang.citadel.config.security.apikey;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ApiKeyRepositoryJpa implements ApiKeyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ApiKey save(ApiKey apiKey) {
        em.persist(apiKey);
        return apiKey;
    }

    @Override
    public List<ApiKey> findByEmail(String email) {
        String jpql = "select a from ApiKey a where a.email = :email";
        TypedQuery<ApiKey> query = em.createQuery(jpql, ApiKey.class);
        query.setParameter("email", email);

        return query.getResultList();
    }

    @Override
    public Optional<ApiKey> findByKey(String key) {
        return Optional.ofNullable(em.find(ApiKey.class, key));
    }

    @Override
    public void remove(ApiKey apiKey) {
        em.remove(apiKey);
    }
}
