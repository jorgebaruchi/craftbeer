package com.beerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beerhouse.domain.BeerCategory;

@Repository
public interface BeerCategoryRepository extends JpaRepository<BeerCategory, Long> {

}
