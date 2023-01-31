package com.Project.Project.data_acess.repositorys;

import com.Project.Project.data_acess.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserInterface extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
