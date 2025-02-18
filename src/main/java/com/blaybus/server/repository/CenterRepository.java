package com.blaybus.server.repository;

import com.blaybus.server.domain.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    @Query("SELECT c FROM Center c WHERE c.address LIKE %:city% AND c.address LIKE %:county% AND c.address LIKE %:region%")
    List<Center> findByAddressContaining(
            @Param("city") String city,
            @Param("county") String county,
            @Param("region") String region
    );

    List<Center> findByCenterNameContaining(String name);
}
