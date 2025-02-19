package com.beerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beerhouse.domain.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>{

}
