package com.neukrang.citadel.lol.domain.summoner;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class SummonerRepositoryJpa implements SummonerRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public String save(Summoner summoner) {
        em.persist(summoner);
        return summoner.getPuuid();
    }

    @Override
    public Optional<Summoner> findByPuuid(String puuid) {
        return Optional.ofNullable(em.find(Summoner.class, puuid));
    }

    @Override
    public Optional<Summoner> findByName(String name) {
        String jpql = "select s from Summoner s where s.name = :name";
        TypedQuery<Summoner> query = em.createQuery(jpql, Summoner.class);
        query.setParameter("name", name);

        return query.getResultStream().findFirst();
    }
}
