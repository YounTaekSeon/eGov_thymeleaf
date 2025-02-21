/*
 * package egovframework.example.sample.web;
 * 
 * import javax.servlet.http.HttpSession; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import egovframework.example.sample.service.EmployeeVO;
 * 
 * @Controller public class LoginController {
 * 
 * //@PostMapping("/login")
 * 
 * @PostMapping("/post_list") public String login(@RequestParam EmployeeVO
 * employeeVO, HttpSession session) { session.setAttribute("loggedInUser",
 * employeeVO); return "redirect:/home"; // 로그인 후 홈으로 이동 }
 * 
 * @GetMapping("/logout") public String logout(HttpSession session) {
 * session.invalidate(); // 세션 초기화 (로그아웃) return "redirect:/login"; } }
 */