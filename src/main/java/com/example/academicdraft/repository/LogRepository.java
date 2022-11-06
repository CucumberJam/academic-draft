package com.example.academicdraft.repository;


import com.example.academicdraft.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends JpaRepository<Log, Long> {

}
