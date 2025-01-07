package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {}
