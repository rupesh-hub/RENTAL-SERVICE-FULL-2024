package com.rentme.app.repository;

import com.rentme.app.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query(value = "SELECT C FROM Conversation C WHERE C.createdBy = :username")
    List<Conversation> getConversations(@Param("username") String username);

}
