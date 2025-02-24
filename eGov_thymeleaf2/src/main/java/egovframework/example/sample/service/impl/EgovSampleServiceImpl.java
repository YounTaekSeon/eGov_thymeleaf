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
package egovframework.example.sample.service.impl;

import java.util.Date;
import java.util.List;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.EmployeeVO;
import egovframework.example.sample.service.SampleSearchVO;
import egovframework.example.sample.service.SampleVO;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
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

@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl implements EgovSampleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);

	/** SampleDAO */
	@Resource(name="sampleMapper")
	private SampleMapper sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	
	private  EgovSampleService sampleService;

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public String insertPost(SampleVO sampleVO) throws Exception {
		String lastPostId = sampleDAO.getLastPostId();
		
		String nextPostId = (lastPostId == null || lastPostId.isEmpty()) 
                			? "00000001" 
                			: String.format("%08d", Integer.parseInt(lastPostId) + 1);
		
		sampleVO.setPostId(nextPostId);
		
		sampleDAO.insertPost(sampleVO);
		
		LOGGER.info("게시물 등록 완료 ID: {}", nextPostId);
		
		return "게시판이 성공적으로 등록되었습니다.";
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateSample(SampleVO vo) throws Exception {
		sampleDAO.updateSample(vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteSample(SampleVO vo) throws Exception {
		sampleDAO.deleteSample(vo);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public SampleVO selectPostById(int postId) throws Exception {
		SampleVO resultVO = sampleDAO.selectPostById(postId);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<SampleVO> selectPostList(SampleSearchVO searchVO) throws Exception {
		return sampleDAO.selectPostList(searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectSampleListTotCnt(SampleSearchVO searchVO) {
		return sampleDAO.selectSampleListTotCnt(searchVO);
	}

	/**
	 * 유저 정보를 불러온다.
	 * @param employeeId - 조회할 정보가 담긴 ID
	 * @return 유저 정보
	 * @exception
	 */
	@Override
	public EmployeeVO selectEmployeeById(String employeeId) throws Exception {
	    LOGGER.debug("selectEmployeeById - employeeId: {}", employeeId);
	    
	    EmployeeVO resultVO = sampleDAO.selectEmployeeById(employeeId);
	    if (resultVO == null) {
	        throw processException("info.nodata.msg");
	    }
	    return resultVO;
	}

}
