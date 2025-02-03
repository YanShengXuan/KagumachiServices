package tw.com.services.kagumachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.model.Suppliers;

import java.util.List;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {

	@Query("SELECT DISTINCT s.name FROM Suppliers s")
	List<String> findAllSupplierNames();
	
	List<Suppliers> findByNameAndSubCategory(String name, SubCategory subCategory);

    List<Suppliers> findByNameContainingIgnoreCase(String name);
    
    List<Suppliers> findByName(String supplierName);
    
    List<Suppliers> findBySubCategory(SubCategory subCategory);
}

