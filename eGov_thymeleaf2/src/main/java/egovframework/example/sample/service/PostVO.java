package egovframework.example.sample.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostVO {
    private String postId;
    private String postTitle;
    private String postCtt;
    private String regrEmpno;
    private String empnm;		// SampleVO에 없던거
    private Date regDt;
    private String mdfrEmpno;
    private Date mdfcnDt;

    public String getPostId() { return postId; }
    public void setPostId(String postId) { this.postId = postId; }

    public String getPostTitle() { return postTitle; }
    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }

    public String getPostCtt() { return postCtt; }
    public void setPostCtt(String postCtt) { this.postCtt = postCtt; }

    public String getRegrEmpno() { return regrEmpno; }
    public void setRegrEmpno(String regrEmpno) { this.regrEmpno = regrEmpno; }

    public String getEmpnm() { return empnm; }
    public void setEmpnm(String empnm) { this.empnm = empnm; }

    public Date getRegDt() { return regDt; }
    public void setRegDt(Date regDt) { this.regDt = regDt; }

    public String getMdfrEmpno() { return mdfrEmpno; }
    public void setMdfrEmpno(String mdfrEmpno) { this.mdfrEmpno = mdfrEmpno; }

    public Date getMdfcnDt() { return mdfcnDt; }
    public void setMdfcnDt(Date mdfcnDt) { this.mdfcnDt = mdfcnDt; }
    
    public void generatePostId(int countToday) {
    	if(this.regDt != null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		String datePart = sdf.format(this.regDt);
    		
    		String uId = String.format("%04d", countToday + 1);
    		
    		this.postId = datePart + uId;
    	}
    	
    }
}
