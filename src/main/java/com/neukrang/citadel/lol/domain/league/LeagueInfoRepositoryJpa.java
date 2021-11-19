package com.neukrang.citadel.lol.domain.league;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LeagueInfoRepositoryJpa implements LeagueInfoRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(LeagueInfo leagueInfo) {
        em.persist(leagueInfo);
        return leagueInfo.getId();
    }

    @Override
    public LeagueInfo find(Long id) {
        return em.find(LeagueInfo.class, id);
    }
}
