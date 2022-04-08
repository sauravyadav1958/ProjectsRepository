package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@Query(value="select c from table c where c.name = ?1",nativeQuery = true)
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email, String password);
     
}
