package com.Project.Project.DataAcess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserInterface extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
