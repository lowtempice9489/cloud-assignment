package com.example.cloudassignment.member.repository;

import com.example.cloudassignment.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
