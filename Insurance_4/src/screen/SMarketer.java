package screen;

import java.util.ArrayList;
import java.util.List;

import dataList.Lists;
import employee.Employee;
import global.Util;
import salesExpense.SalesExpense;
import uwRule.UwRule;

public class SMarketer {

	private Employee employee;
	private Lists lists;
	private ArrayList<String> insentiveList; // 임시

	public SMarketer(Employee employee, Lists lists) {

		this.lists = lists;
		this.employee = employee;
		this.insentiveList = new ArrayList<String>();

		MarketerMain();
	}

	public void MarketerMain() {
		boolean check = false;
		while (!check) {
			System.out.println("---------------Marketer---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1.인센티브 기준 수립");
			System.out.println("2.인센티브 기준 확인");
			System.out.println("3.직원 인센티브 등급 부여");
			int select = Util.IntReader("4. 로그아웃");

			if (select == 1) {
				createInsentive();
			} else if (select == 2) {
				getInsentiveList();
			} else if (select == 3) {
				enterGrade();
			} else if (select == 4) {
				check = true;
			} else {
				System.out.println("Select Error retry");
			}
		}
	}

	public void enterGrade() {
	      List<Employee> employeeList = this.lists.getEmployeeList();
	      Employee employee = null;
	      for (int i = 0; i < employeeList.size(); i++) {
	         System.out.println(employeeList.get(i).toString());
	      }
	      boolean flag = false;
	      while(!flag) {
	         int pIE = Util.IntReader("인센티브 등급을 부여할 직원 번호를 입력해주세요");
	         //Provide Incentive to Employee
	         if(pIE == 0) return;
	         for(Employee temp : employeeList) {
	            if(temp.getEmployeeIdx() == pIE) { 
	            flag = true;
	            employee = temp;   
	            }
	         }
	         if(employee == null)
	         System.out.println("존재하지 않는 직원 idx입니다.");
	      }   
	      this.lists.modifyEmployeeGrade(employee.getEmployeeIdx(), Util.StringReader("인센티브 등급 입력하기"));
	      System.out.println("인센티브 등급 부여가 완료되었습니다.");
	   }

	public void createInsentive() {
	      boolean flag = false;
	      boolean flag2 = false;
	      SalesExpense salesExpense = new SalesExpense();
	      String iTitle = Util.StringReader("인센티브기준정책 제목: ");
	      if(iTitle.equals("0")) return;
	      while(!flag) {
	         if(!iTitle.equals("")) {
	            flag = true; 
	            break;
	         } else iTitle = Util.StringReader("제목을 입력하지 않으셨습니다.\n제목을 입력해주세요: ");
	      }
	      salesExpense.setTitle(iTitle);
	      String iContent = Util.StringReader("인센티브기준정책 내용: ");
	      if(iContent.equals("0")) return;
	      while(!flag2) {
	         if(!iContent.equals("")) {
	            flag2 = true; 
	            break;   
	         } else iContent = Util.StringReader("내용을 입력하지 않으셨습니다.\n내용을 입력해주세요: ");
	      }
	      salesExpense.setContent(iContent);
	      salesExpense.setEmployeeIdx(this.employee.getEmployeeIdx());
	      this.lists.addSalesExpense(salesExpense);
	      System.out.println("인센티브 기준 수립이 완료되었습니다.");
	      }


	public boolean getInsentiveList() {
		System.out.println("---------------InsentiveRule LIST---------------");
		List<SalesExpense> salesExpenseList = this.lists.getSalesExpenseList();
		if (salesExpenseList.size() == 0) {
			System.out.println("등록된 인센티브 기준이 없습니다.");
			return false;
		}
		for (SalesExpense temp : salesExpenseList) {
			System.out.println("[정책 번호]");
			System.out.println(temp.getSalesExpenseIdx());
			System.out.println("[정책 제목]");
			System.out.println(temp.getTitle());
		    System.out.println("[정책 내용]");
		    System.out.println(temp.getContent());
		    System.out.println("[직원 번호]");
		    System.out.println(temp.getEmployeeIdx());
		    System.out.println("-----------------------------------------");
		}
		return true;
	}

	public Lists getLists() {
		return this.lists;
	}
}
