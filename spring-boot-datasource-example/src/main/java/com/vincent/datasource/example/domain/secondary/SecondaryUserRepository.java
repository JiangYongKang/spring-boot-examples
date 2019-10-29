package com.vincent.datasource.example.domain.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryUserRepository extends JpaRepository<SecondaryUserInfo, Long> {
}
