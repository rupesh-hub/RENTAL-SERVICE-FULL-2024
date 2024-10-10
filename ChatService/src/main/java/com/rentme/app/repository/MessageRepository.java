package com.rentme.app.repository;

import com.rentme.app.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT M FROM Message M WHERE M.conversation.id = :conversationId")
    List<Message> allMessages(@Param("conversationId") Long conversationId);

}
