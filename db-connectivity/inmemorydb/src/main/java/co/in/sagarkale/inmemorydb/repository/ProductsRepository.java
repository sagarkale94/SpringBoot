package co.in.sagarkale.inmemorydb.repository;

import co.in.sagarkale.inmemorydb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    <S extends Product> S save(S entity);
}
