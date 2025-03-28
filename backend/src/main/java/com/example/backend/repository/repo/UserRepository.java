
package com.example.backend.repository.repo;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//API TÌM KIẾM
    // Tìm kiếm theo họ tên (chính xác)
    List<User> findByName(String name);

    // Tìm kiếm theo họ tên (không chính xác)
    List<User> findByNameContaining(String name);

    // Tìm kiếm theo ngày sinh
    List<User> findByDateOfBirth(Date dateOfBirth);

    // Tìm kiếm theo vai trò (roleId)
    List<User> findByRoleId(int roleId);

    // Tìm kiếm theo trạng thái
    List<User> findByStatusCode(String statusCode);

    // Tìm kiếm nâng cao theo nhiều tiêu chí
    @Query("SELECT u FROM User u WHERE " +
           "(:name IS NULL OR u.name LIKE %:name%) AND " +
           "(:dateOfBirth IS NULL OR u.dateOfBirth = :dateOfBirth) AND " +
           "(:roleId IS NULL OR u.roleId = :roleId) AND " +
           "(:statusCode IS NULL OR u.statusCode = :statusCode)")
    List<User> searchUsers(@Param("name") String name,
                           @Param("dateOfBirth") Date dateOfBirth,
                           @Param("roleId") Integer roleId,
                           @Param("statusCode") String statusCode);

// API CẬP NHẬT

}