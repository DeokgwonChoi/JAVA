package main;

import dataList.Lists;
import employee.Employee;
import global.Util;
import screen.SAccidentManager;
import screen.SDamageAssessor;
import screen.SDesigner;
import screen.SLoginScreen;
import screen.SManager;
import screen.SMarketer;
import screen.Salesman;
import screen.SSignUpScreen;
import screen.SUw;
import screen.SUser;
import user.User;

public class RunFrame {

	private Lists lists;

	private User user;
	private Employee employee;

	public RunFrame() {
		this.lists = new Lists();
		boolean start = false;

		while (!start) {
			int select = Util.IntReader("1. 로그인 \n2. 회원가입 \n3. exit");
			switch (select) {
			case 1:
				SLoginScreen pLogin = new SLoginScreen(this.lists);
				////////////////////// 사용자 장착//////////////////////
				String type = pLogin.getType();
				if (type == null)
					System.out.println("일치하는 회원정보가 존재하지 않습니다.");
				else
					switch (type) {
					case "user":
						this.user = pLogin.getUser();
						runUser(this.user, this.lists);
						break;
					case "employee":
						this.employee = pLogin.getEmployee();
						runEmployee(this.employee, this.lists);
						break;
					default:
						System.out.println("not match account");
						break;
					}
				break;
			case 2:
				SSignUpScreen pSignUp = new SSignUpScreen(this.lists);
				SLoginScreen pLogin2 = new SLoginScreen(this.lists);
				////////////////////// 사용자 장착//////////////////////
				String type2 = pLogin2.getType();
				if (type2 == null)
					System.out.println("일치하는 회원정보가 존재하지 않습니다.");
				else if (type2.equals("user")) {
					this.user = pLogin2.getUser();
					runUser(this.user, this.lists);
				} else if (type2.equals("employee")) {
					this.employee = pLogin2.getEmployee();
					runEmployee(this.employee, this.lists);
				} else {
					System.out.println("not match account");
				}
				break;
			case 3:
				start = true;
				break;
			default:
				System.out.println("정확한 번호를 입력해주세요.");
				break;
			}
		}
	}

	////////////////////// Run User//////////////////////
	public boolean runUser(User user, Lists lists) {
		SUser pUser = new SUser(user, lists);
		return true;
	}

	////////////////////// Run Employee//////////////////////
	public boolean runEmployee(Employee employee, Lists lists) {
		String role = employee.getRole();
		switch (role) {
		case "AccidentManager":
			SAccidentManager accidentManager = new SAccidentManager(employee,lists);
			this.lists = accidentManager.getLists(); //DB저장 
			return true;
		case "DamageAssessor":
			SDamageAssessor damageAssessor = new SDamageAssessor(employee, lists);
			this.lists = damageAssessor.getLists();
			return true;
		case "Designer":
			SDesigner designer = new SDesigner(employee, lists);
			this.lists = designer.getLists();
			return true;
		case "Manager":
			SManager manager = new SManager(employee, lists);
			this.lists = manager.getLists();
			return true;
		case "Marketer":
			SMarketer marketer = new SMarketer(employee, lists);
			this.lists = marketer.getLists();
			return true;
		case "Salesman":
			Salesman salesman = new Salesman(employee, lists);
			this.lists = salesman.getLists();
			return true;
		case "UW":
			SUw uw = new SUw(employee, lists);
			this.lists = uw.getLists();
			return true;
		default:
			System.out.println("잘못된 role 입니다.");
		}
		return false;
	}
}