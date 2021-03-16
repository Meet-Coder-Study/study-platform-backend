package com.meetcoder.web.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meetcoder.web.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findMemberByGithubId(String githubId);
}
