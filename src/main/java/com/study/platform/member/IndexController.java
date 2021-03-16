package com.study.platform.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.platform.auth.session.LoginUser;
import com.study.platform.auth.session.SessionUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final HttpSession session;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		if (user != null){
			model.addAttribute("userGithubId", user.getGithubId());
			model.addAttribute("pictureUrl", user.getPictureUrl());
		}
		return "index";
	}
}
