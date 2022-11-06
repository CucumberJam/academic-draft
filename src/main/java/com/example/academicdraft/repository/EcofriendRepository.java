package com.example.academicdraft.repository;

import com.example.academicdraft.entity.Ecofriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcofriendRepository extends JpaRepository<Ecofriend, Long> {
}
