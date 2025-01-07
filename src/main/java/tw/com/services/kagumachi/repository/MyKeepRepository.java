package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.MyKeep;

public interface MyKeepRepository extends JpaRepository<MyKeep, Integer> {}