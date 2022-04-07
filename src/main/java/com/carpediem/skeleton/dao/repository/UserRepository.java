package com.carpediem.skeleton.dao.repository;

import com.carpediem.skeleton.dao.entity.User;
import com.carpediem.skeleton.model.enumaration.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByStatus(StatusEnum status);
}
