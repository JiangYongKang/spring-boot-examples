package com.vincent.datasource.example.domain.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryUserRepository extends JpaRepository<PrimaryUserInfo, Long> {
}
