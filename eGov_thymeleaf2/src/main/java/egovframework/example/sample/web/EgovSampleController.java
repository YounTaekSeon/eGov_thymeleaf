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

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

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
		// TODO: 로그인 어떻게 할 것인지 생각
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
		// TODO: 고민. 세션에서 불러올 것인지, GetMapping 때 create.html를 호출하면서 input hidden으로 값을 넣고 그걸 불러올 것인지
	    EmployeeVO loggedInUser = getLoggedInUser(session);
	    sampleVO.setRegrEmpno(loggedInUser.getEmpno());

	    sampleService.insertPost(sampleVO);
		
		return "redirect:/post_list";
	}
	
	/**
	 * 게시글 수정화면 호출.
	 * @return post_edit.html
	 * @exception Exception
	 */	
	@GetMapping("/post_edit/{postId}")
	public String getPostEdit(@PathVariable("postId") String postId, Model model, HttpSession session) throws Exception {
	    PostVO postDetail = sampleService.selectPostById(postId);
	    if (postDetail == null) {
	        model.addAttribute("errorMessage", "데이터 없음");
	        return "common/common_error";
	    }

	    EmployeeVO loggedInUser = getLoggedInUser(session);
	    model.addAttribute("employeeVO", loggedInUser);
	    model.addAttribute("sampleVO", postDetail);

	    return "post_edit";
	}
	
	/**
	 * 게시글 수정.
	 * @return post_create.html
	 * @exception Exception
	 */	
	@PostMapping(value="/post_edit")
	public String editPost(@ModelAttribute SampleVO sampleVO, HttpSession session) throws Exception{
		// TODO: 고민. 세션에서 불러올 것인지, GetMapping 때 edit.html를 호출하면서 input hidden으로 값을 넣고 그걸 불러올 것인지		
	    EmployeeVO loggedInUser = getLoggedInUser(session);
	    sampleVO.setMdfrEmpno(loggedInUser.getEmpno());

	    sampleService.updatePost(sampleVO);

	    return "redirect:/post_view/" + sampleVO.getPostId();
	}
	
	/**
	 * 게시글 삭제.
	 * @return post_list.html
	 * @exception Exception
	 */		
	@PostMapping("/post_delete")
	public String deletePost(@ModelAttribute SampleVO sampleVO, @RequestParam("postId") String postId, HttpSession session) throws Exception {
	    EmployeeVO loggedInUser = getLoggedInUser(session);
	    sampleVO.setDelEmpno(loggedInUser.getEmpno());  // 삭제한 사용자의 사번 설정
	    sampleVO.setPostId(postId); 					// 삭제할 postId 설정

	    log.debug("Deleting post - postId: {}, delEmpno: {}", sampleVO.getPostId(), sampleVO.getDelEmpno());

	    sampleService.deletePost(sampleVO); 			// SampleVO를 넘겨야 함

	    return "redirect:/post_list";
	}

	
	
	/**
	 * 세션에서 로그인된 사용자 정보를 가져오는 공통 메서드
	 */
	private EmployeeVO getLoggedInUser(HttpSession session) {
	    EmployeeVO loggedInUser = (EmployeeVO) session.getAttribute("loggedInUser");

	    // TODO: 위에 로그인 값이 없으면 default값 들어가도록 해놨는데 이거 놔둘지 말지 고민
	    if (loggedInUser == null) {
	        loggedInUser = new EmployeeVO();
	        loggedInUser.setEmpnm("Guest");  // 기본 이름
	        loggedInUser.setEmpno("000000"); // 기본 사번
	    }

	    return loggedInUser;
	}
	
	private String transDateFormat(Date dated) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String formattedDate = sdf.format(dated);
		
		return formattedDate;
	}
}
