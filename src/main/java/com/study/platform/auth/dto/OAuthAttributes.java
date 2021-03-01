package com.study.platform.auth.dto;

import java.util.Map;

import com.study.platform.member.domain.Member;
import com.study.platform.member.domain.enums.Authority;
import com.study.platform.member.domain.enums.DeveloperType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String githubId;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String githubId,
		String name, String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.githubId = githubId;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		return ofGithub(userNameAttributeName, attributes);
	}

	public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.githubId((String) attributes.get("login"))
			.name((String) attributes.get("name"))
			.email((String) attributes.get("email"))
			.picture((String) attributes.get("picture"))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	public Member toEntity() {
		return Member.builder()
			.githubId(githubId)
			.name(name)
			.email(email)
			.developerType(DeveloperType.UNKNOWN)
			.authority(Authority.MEMBER)
			.build();
	}
}
