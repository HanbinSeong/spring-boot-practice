package com.example.one_hour_project.repository;

import com.example.one_hour_project.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
