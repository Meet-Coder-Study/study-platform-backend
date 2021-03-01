package com.study.platform.auth.session;

import java.io.Serializable;

import com.study.platform.member.domain.Member;
import com.study.platform.member.domain.enums.DeveloperType;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private String githubId;
	private String name;
	private String email;
	private String pictureUrl;
	private DeveloperType developerType;

	public SessionUser(Member member) {
		this.githubId = member.getGithubId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.pictureUrl = member.getPictureUrl();
		this.developerType = member.getDeveloperType();
	}
}
