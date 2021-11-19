package com.neukrang.citadel.lol.domain.league;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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
    public Optional<LeagueInfo> find(Long id) {
        return Optional.ofNullable(em.find(LeagueInfo.class, id));
    }
}
