package com.sai.Uniblox.repository;

import com.sai.Uniblox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

