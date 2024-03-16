package com.mq.chat.data.repository;

import com.mq.chat.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberIdOrNickname(String memberId, String nickname);
    List<Member> findAllByOrderById();
}
