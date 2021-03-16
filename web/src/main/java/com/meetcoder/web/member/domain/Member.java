package com.meetcoder.web.member.domain;

import com.meetcoder.web.member.domain.enums.Authority;
import com.meetcoder.web.member.domain.enums.DeveloperType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    public Member(final Long id, final String githubId, final String email, final String name, final String pictureUrl,
                  final DeveloperType developerType,
                  final Authority authority) {
        this.id = id;
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.developerType = developerType;
        this.authority = authority;
    }
}
