package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Website;

public interface WebsiteRepository extends JpaRepository<Website, Integer> {}
