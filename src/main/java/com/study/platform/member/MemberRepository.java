package com.study.platform.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.study.platform.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("select m from Member m where m.githubId = :githubId")
	Optional<Member> findMemberByGithubId(String githubId);
}
