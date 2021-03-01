package com.study.platform.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.study.platform.member.domain.enums.Authority;
import com.study.platform.member.domain.enums.DeveloperType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String githubId;

    private String email;

    private String name;

    private String pictureUrl;

    @Enumerated(value = EnumType.STRING)
    private DeveloperType developerType;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(Long id, String githubId, String email, String name, String pictureUrl,
        DeveloperType developerType,
        Authority authority) {
        this.id = id;
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.developerType = developerType;
        this.authority = authority;
    }
}
