package com.study.platform.blog.domain;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.study.platform.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    private URL link;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Builder
    public Blog(Long id, URL link, Member member, String title) {
        this.id = id;
        this.link = link;
        this.member = member;
        this.title = title;
    }
}

