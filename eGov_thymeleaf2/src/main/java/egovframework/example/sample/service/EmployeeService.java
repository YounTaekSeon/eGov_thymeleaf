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

import java.util.List;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
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
public interface EmployeeService {

	/**
	 * 사원을 등록한다.
	 * @param employeeVo - 등록할 정보가 담긴 EmployeeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertEmployee(EmployeeVO employeeVo) throws Exception;

	/**
	 * 사원정보를 수정한다.
	 * @param employeeVo - 수정할 정보가 담긴 EmployeeVO
	 * @return void형
	 * @exception Exception
	 */
	void updateEmployee(EmployeeVO employeeVo) throws Exception;

	/**
	 * 사원정보를 삭제한다.
	 * @param employeeVo - 삭제할 정보가 담긴 EmployeeVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteEmployee(EmployeeVO employeeVo) throws Exception;

	/**
	 * 사원정보를 조회한다.
	 * @param employeeVo - 조회할 정보가 담긴 EmployeeVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	EmployeeVO selectEmployeeById(String empno) throws Exception;
}
