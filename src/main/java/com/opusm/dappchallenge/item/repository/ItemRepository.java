package com.opusm.dappchallenge.item.repository;

import com.opusm.dappchallenge.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

}