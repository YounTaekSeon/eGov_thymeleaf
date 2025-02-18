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

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EgovSampleController {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EgovSampleController.class);
	
	private final EgovSampleService sampleService;
	
	public EgovSampleController(EgovSampleService sampleService) {
		this.sampleService = sampleService;		
	}
	
	@GetMapping("/test.do")
	public String getSampleList(@ModelAttribute("sampleDefaultVo") SampleDefaultVO sampleVo,Model model) throws Exception {
		List<SampleVO> sampleList = sampleService.selectSampleList(sampleVo);
		model.addAttribute("sampleList", sampleList);
		
		log.info("Sample List: {}", sampleList);
		
		return "home";
	}
	
	 
}
