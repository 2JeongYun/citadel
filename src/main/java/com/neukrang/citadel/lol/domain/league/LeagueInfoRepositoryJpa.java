package com.neukrang.citadel.lol.domain.league;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<LeagueInfo> findBySummoner(Summoner summoner) {
        String jpql = "select l from LeagueInfo l where l.summoner = :summoner";
        TypedQuery<LeagueInfo> query = em.createQuery(jpql, LeagueInfo.class);
        query.setParameter("summoner", summoner);

        return query.getResultStream().collect(Collectors.toList());
    }
}
