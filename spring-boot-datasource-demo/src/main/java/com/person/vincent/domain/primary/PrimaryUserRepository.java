package com.person.vincent.domain.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryUserRepository extends JpaRepository<PrimaryUserInfo, Long> {
}
