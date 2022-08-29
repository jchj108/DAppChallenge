package com.opusm.dappchallenge.user.repository;

import com.opusm.dappchallenge.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
