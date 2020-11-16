package spring.boot.mybatis.module;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: vincent
 * Date: 2020-11-12 10:56
 * Comment:
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
}
