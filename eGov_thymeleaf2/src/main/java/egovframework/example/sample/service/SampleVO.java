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
package egovframework.example.sample.service;

import java.util.Date;

/**
 * @Class Name : SampleVO.java
 * @Description : SampleVO Class
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
public class SampleVO extends SampleSearchVO {

	private static final long serialVersionUID = 1L;

	/** 게시글 ID */
	private String postId;

	/** 게시글제목 */
	private String postTitle;

	/** 게시글내용 */
	private String postCtt;
	
	/** 작성자사원번호 */
	private String regrEmpno;
	
	/** 작성일시 */
	private Date regDt;
	
	/** 수정자사원번호 */
	private String mdfrEmpno;
	
	/** 수정일시 */
	private Date mdfcnDt;
	
	/** 삭제여부 */
	private String delYn;
	
	/** 삭제일시 */
	private Date delDt;
	
	/** 삭제자사원번호 */
	private String delEmpno;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostCtt() {
        return postCtt;
    }

    public void setPostCtt(String postCtt) {
        this.postCtt = postCtt;
    }

    public String getRegrEmpno() {
        return regrEmpno;
    }

    public void setRegrEmpno(String regrEmpno) {
        this.regrEmpno = regrEmpno;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }
    
    public String getMdfrEmpno() {
        return mdfrEmpno;
    }

    public void setMdfrEmpno(String mdfrEmpno) {
        this.mdfrEmpno = mdfrEmpno;
    }
    
    public Date getMdfcnDt() {
        return mdfcnDt;
    }

    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt;
    }
    
    public Date getDelDt() {
        return delDt;
    }

    public void setDelDt(Date delDt) {
        this.delDt = delDt;
    }
    
    public String getDelEmpno() {
        return delEmpno;
    }

    public void setDelEmpno(String delEmpno) {
        this.delEmpno = delEmpno;
    }
    
    public String getdelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
}
