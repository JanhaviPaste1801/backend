package com.app.insurance.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.insurance.model.User;

/**
 * @author jpaste This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link User} that is being
 *         managed.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.role!='SUPERADMIN'")
    public List<User> findAll();

}
