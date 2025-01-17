package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Suppliers;

import java.util.List;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {
    List<Suppliers> findByNameContainingIgnoreCase(String name);
}
