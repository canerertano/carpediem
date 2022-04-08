package com.carpediem.skeleton.dao.repository;

import com.carpediem.skeleton.dao.entity.User;
import com.carpediem.skeleton.model.enumaration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByStatus(Pageable pageable, StatusEnum status);
}
