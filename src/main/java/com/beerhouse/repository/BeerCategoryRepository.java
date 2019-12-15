package com.beerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beerhouse.domain.BeerCategory;

public interface BeerCategoryRepository extends JpaRepository<BeerCategory, Long> {

}
