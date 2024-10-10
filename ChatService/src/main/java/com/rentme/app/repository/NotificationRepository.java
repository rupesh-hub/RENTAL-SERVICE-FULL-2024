package com.rentme.app.repository;

import com.rentme.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT N FROM Notification N WHERE N.recipient = :username")
    List<Notification> getNotifications(@Param("username") String username);

}
