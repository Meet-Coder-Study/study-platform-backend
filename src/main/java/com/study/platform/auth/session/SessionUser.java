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
	private DeveloperType developerType;

	public SessionUser(Member member) {
		this.githubId = member.getGithubId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.developerType = member.getDeveloperType();
	}
}
