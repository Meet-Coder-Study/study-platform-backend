package com.study.platform.blog.domain;

import com.study.platform.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    private String link;

    private String rssLink;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Builder
    public Blog(final Long id, final String link, final String rssLink, final Member member, final String title) {
        this.id = id;
        this.link = link;
        this.rssLink = rssLink;
        this.member = member;
        this.title = title;
    }

    public void updateLink(final String blogLink, final String rssLink, final String title) {
        this.link = blogLink;
        this.rssLink = rssLink;
        this.title = title;
    }
}

