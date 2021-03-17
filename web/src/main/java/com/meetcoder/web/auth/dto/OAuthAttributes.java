package com.meetcoder.web.auth.dto;

import java.util.Map;

import com.meetcoder.web.member.domain.Member;
import com.meetcoder.web.member.domain.enums.Authority;
import com.meetcoder.web.member.domain.enums.DeveloperType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String githubId;
	private String name;
	private String email;
	private String pictureUrl;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String githubId,
		String name, String email, String pictureUrl) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.githubId = githubId;
		this.name = name;
		this.email = email;
		this.pictureUrl = pictureUrl;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		return ofGithub(userNameAttributeName, attributes);
	}

	public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.githubId((String) attributes.get("login"))
			.name((String) attributes.get("name"))
			.email((String) attributes.get("email"))
			.pictureUrl((String) attributes.get("avatar_url"))
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
			.pictureUrl(pictureUrl)
			.build();
	}
}
