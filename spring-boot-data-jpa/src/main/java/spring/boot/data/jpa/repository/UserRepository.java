package spring.boot.data.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.boot.data.jpa.module.User;

import java.util.List;

/**
 * Author: vincent
 * Date: 2020-11-12 11:02
 * Comment:
 */

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    @Query("select u from User u where u.lastName = :lastName")
    List<User> findByLastName(@Param("lastName") String firstName);

}
