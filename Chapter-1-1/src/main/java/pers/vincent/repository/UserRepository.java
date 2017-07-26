package pers.vincent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pers.vincent.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String name);

    @Query("select id, name, age from User u where u.name = :name")
    User findUser(@Param("name") String name);
}
