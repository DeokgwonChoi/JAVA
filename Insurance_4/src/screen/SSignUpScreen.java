package screen;

import java.util.regex.Pattern;

import dataList.Lists;
import employee.Employee;
import global.Util;
import user.User;

public class SSignUpScreen {
   
   private Lists lists;
   
   public SSignUpScreen(Lists lists) {
      this.lists = lists;

      System.out.println("---------------회원가입---------------");
      int select = Util.IntReader("1.고객 회원가입 \n2.직원 회원가입 \n3.뒤로가기");
      switch(select) {
      case 1:
         SignUpUser();
         break;
      case 2:
         SignUpEmployee();
         break;
      default:
         System.out.println("잘못 입력하였습니다.");
         break;
      }
   }
   public User SignUpUser() {
      User user = new User();
      String id = Util.StringReader("아이디를 입력하세요");
      // 
      while(checkId(id)) {
         id = Util.StringReader("중복된 아이디입니다. 다시 입력해주세요.");
      }
      user.setId(id);
      user.setPw(Util.StringReader("비밀번호 입력하세요"));
      user.setName(Util.StringReader("이름을 입력하세요"));
      user.setJob(Util.StringReader("직업를 입력하세요"));
      user.setSsn(Util.StringReader("주민번호를 입력하세요"));
      user.setTel(Util.StringReader("전화번호를 입력하세요"));
      user.setCity(Util.StringReader("도시를 입력하세요."));
      user.setState(Util.StringReader("도를 입력하세요."));
      
      String email = Util.StringReader("이메일을 입력하세요");      
      while(checkEmail(email)) {
         email = Util.StringReader("올바르지 않은 형식입니다. 다시 입력해주세요.");
      }
      user.setEmail(email);
      
      int tempSex =  Util.IntReader("남자는 0, 여자는 1");
      boolean sex = tempSex == 0 ? false : true;
      user.setSex(sex);
      this.lists.addUser(user);
      return user;
   }

public Employee SignUpEmployee() {
      Employee employee = new Employee();
      String id = Util.StringReader("아이디를 입력하세요");
      while(checkId(id)) {
         id = Util.StringReader("중복된 아이디입니다. 다시 입력해주세요.");
      }
      employee.setId(id);
      employee.setPw(Util.StringReader("비밀번호 입력하세요"));
      employee.setName(Util.StringReader("이름을 입력하세요"));
      employee.setRole(Util.StringReader("직책을 입력하세요"));
      employee.setTel(Util.StringReader("전화번호를 입력하세요"));
      while(true) {
         System.out.println("직무를 입력해주세요.");
         int roleNum = Util.IntReader("보험설계사(1), 보험개발자(2), UW(3), 손해사정사(4), 손해관리직원(5), 영업조직 관리자(6), 계약관리자(7)");
         String tempRole = findRole(roleNum);
         if(tempRole != "again") {
            employee.setRole(tempRole);
            break;
         }
      }
      employee.setAccountNumber(Util.StringReader("계좌 번호를 입력하세요"));
      this.lists.addEmployee(employee);
      return employee;
   }   
   private String findRole(int role) {
      switch (role) {
      case 1:
         return "Salesman";
      case 2:
         return "Designer";
      case 3:
         return "UW";
      case 4:
         return "DamageAssessor";
      case 5:
         return "AccidentManager";
      case 6:
         return "Marketer";
      case 7:
         return "Manager";
      default :
         return "again";
      }
   }
   //중복 아이디 확인
   private boolean checkId(String id) {
      for(Employee employee : this.lists.getEmployeeList()) {
         if(employee.getId().equals(id))  return true;
      }
      for(User user : this.lists.getUserList()) {
         if(user.getId().equals(id)) return true;
      }
      return false;
   }
   //email 형식 확인
   private boolean checkEmail(String email) {
      String pattern2 = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
      if(Pattern.matches(pattern2, email)) {
          return false;
      } else {            
          System.out.println("올바른 이메일 형식이 아닙니다. ");
          return true;
      }
}
   public Lists getLists() {
      return this.lists;
   }
}