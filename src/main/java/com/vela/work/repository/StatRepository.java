package com.vela.work.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vela.work.entities.Stats;

@Repository
public interface StatRepository extends JpaRepository<Stats, Long>{
	
	public Stats findByCardNo(String cardNo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Stats stats SET stats.records = stats.records +1 WHERE stats.cardNo = ?1")
	public void updateStatistics(String cardNo);

}
