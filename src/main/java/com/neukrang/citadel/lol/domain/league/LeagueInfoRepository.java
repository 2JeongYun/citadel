package com.neukrang.citadel.lol.domain.league;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LeagueInfoRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(LeagueInfo leagueInfo) {
        em.persist(leagueInfo);
        return leagueInfo.getId();
    }

    public LeagueInfo find(Long id) {
        return em.find(LeagueInfo.class, id);
    }
}
