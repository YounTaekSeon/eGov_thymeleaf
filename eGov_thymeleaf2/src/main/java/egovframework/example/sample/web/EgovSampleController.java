/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.EmployeeVO;
import egovframework.example.sample.service.PostVO;
import egovframework.example.sample.service.SampleSearchVO;
import egovframework.example.sample.service.SampleVO;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2009.03.16 최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *      Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EgovSampleController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EgovSampleController.class);

	private final EgovSampleService sampleService;

	public EgovSampleController(EgovSampleService sampleService) {
		this.sampleService = sampleService;
	}

	/**
	 * 게시글 리스트 화면 호출.
	 * @return post_list.html
	 * @exception Exception
	 */
	@GetMapping(value = "/post_list")
	public String getPostList(@ModelAttribute SampleSearchVO searchVO, HttpSession session, Model model) throws Exception {

		// 로그인 정보가 세션에 없으면 자동 로그인
		if (session.getAttribute("loggedInUser") == null) {
			EmployeeVO user = sampleService.selectEmployeeById("00000001");
			if (user != null) {
				session.setAttribute("loggedInUser", user);
			}
		}
		
		log.info("현재 세션 사용자: {}", session.getAttribute("loggedInUser"));

		List<PostVO> postList = sampleService.selectPostList(searchVO);
		model.addAttribute("postList", postList);
		model.addAttribute("searchVO", searchVO);

		/* log.info("Sample List: {}", postList); */

		return "post_list";
	}
	
	/**
	 * 검색 후 게시글 리스트 호출.
	 * @return post_list.html
	 * @exception Exception
	 */
	@PostMapping("/post_list")
	public String searchPostList(@ModelAttribute SampleSearchVO searchVO, Model model) throws Exception {
		/*
		 * log.info("searchVO: {}", searchVO); log.info("searchCondition: {}",
		 * searchVO.getSearchCondition()); log.info("searchKeyword: {}",
		 * searchVO.getSearchKeyword());
		 */

		List<PostVO> postList = sampleService.selectPostList(searchVO);
		model.addAttribute("postList", postList);
		model.addAttribute("searchVO", searchVO);

		return "post_list";
	}
	
	/**
	 * 게시글 상세화면 호출.
	 * @return post_view.html
	 * @exception Exception
	 */
	@GetMapping("/post_view/{postId}")
	public String getPostView(@PathVariable("postId") String postId, Model model) throws Exception {
	    try {
	        PostVO postDetail = sampleService.selectPostById(postId);
	        log.info("Post: {}", postDetail);

	        if (postDetail == null) {
	        	model.addAttribute("errorMessage", "데이터 없음");
	            return "common/common_error";
	        }

	        model.addAttribute("postDetail", postDetail);
	        return "post_view";

	    } catch (Exception e) {
	        model.addAttribute("errorMessage", e.getMessage());
	        return "common/common_error";
	    }
	}	

	/**
	 * 게시글 작성화면 호출.
	 * @return post_create.html
	 * @exception Exception
	 */	
	@GetMapping(value = "/post_create")
	public String addPost(HttpSession session, Model model) throws Exception {
	    EmployeeVO loggedInUser = getLoggedInUser(session);

	    SampleVO sampleVO = new SampleVO();
	    sampleVO.setRegrEmpno(loggedInUser.getEmpno());

	    model.addAttribute("employeeVO", loggedInUser);
	    model.addAttribute("sampleVO", sampleVO);

		return "post_create";
	}

	/**
	 * 게시글 작성.
	 * @return post_create.html
	 * @exception Exception
	 */	
	@PostMapping(value="/post_create")
	public String savePost(@ModelAttribute SampleVO sampleVO, HttpSession session) throws Exception{
	    EmployeeVO loggedInUser = getLoggedInUser(session);
	    sampleVO.setRegrEmpno(loggedInUser.getEmpno());

	    sampleService.insertPost(sampleVO);
		
		return "redirect:/post_list";
		
	}
	
	/**
	 * 세션에서 로그인된 사용자 정보를 가져오는 공통 메서드
	 */
	private EmployeeVO getLoggedInUser(HttpSession session) {
	    EmployeeVO loggedInUser = (EmployeeVO) session.getAttribute("loggedInUser");

	    if (loggedInUser == null) {
	        loggedInUser = new EmployeeVO();
	        loggedInUser.setEmpnm("Guest");  // 기본 이름
	        loggedInUser.setEmpno("000000"); // 기본 사번
	    }

	    return loggedInUser;
	}	

}
