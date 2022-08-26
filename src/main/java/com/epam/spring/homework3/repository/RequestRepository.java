package com.epam.spring.homework3.repository;

import com.epam.spring.homework3.model.entity.Request;
import com.epam.spring.homework3.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> getAllRequestsByUserId(Long userId);

    @Modifying
    @Query("UPDATE Request r SET r.status = ?1 WHERE r.id = ?2")
    void updateRequestStatusById(Status status, Long id);
}
