package com.neukrang.citadel.lol.domain.summoner;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public Summoner find(String puuid) {
        return em.find(Summoner.class, puuid);
    }
}
