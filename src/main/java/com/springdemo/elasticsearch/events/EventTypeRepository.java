package com.springdemo.elasticsearch.events;

import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@NamedNativeQuery(name = "EventType", query = "select e.id, e.type, e.event, e.createTs, e.updateTs, e.udevId from EventType e where e.udevId = :udevId", resultSetMapping = "GetInfoResult")
@NamedNativeQuery(name = "EventTypes", query = "select e.id, e.type, e.event, e.createTs, e.updateTs, e.udevId from EventType e where e.createTs >= :createTs order by e.createTs DESC", resultSetMapping = "GetAllInfoResult")
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    @Transactional
    @Modifying
    @Query("update EventType e set e.updateTs = ?1 where e.udevId like ?2")
    int createInfo(@NonNull Long updateTs, @NonNull String udevId);

    @Transactional
    @Modifying
    @Query("update EventType e set e.event = :event where e.udevId like :udevId and e.type like :type")
    int updateInfo(@NonNull @Param("event") String event, @NonNull @Param("udevId") String udevId, @NonNull @Param("type") String type);

    @Query(name = "EventType")
    List<EventType> findByUdevId(String udevId);
}