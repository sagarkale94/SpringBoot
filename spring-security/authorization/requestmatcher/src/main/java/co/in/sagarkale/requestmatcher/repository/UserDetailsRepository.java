package co.in.sagarkale.requestmatcher.repository;

import co.in.sagarkale.requestmatcher.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    @Override
    Users save(Users entity);
}
