package com.poc.passwordresettoken.domain.repository;

import com.poc.passwordresettoken.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query("""
            update #{#entityName}
               set deactivationDate = :#{T(java.time.OffsetDateTime).now()}
             where id = :#{#user.id}
               and modificationDate = :#{#user.modificationDate}
               and deactivationDate is null
            """)
    void deactivate(@Param("user") User user);

    List<User> findByDeactivationDateIsNull();
}
