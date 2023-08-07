package screen;

import java.util.List;

import contract.Contract;
import dataList.Lists;
import employee.Employee;
import global.Util;
import uwRule.UwRule;

public class SUw {

	private Employee employee = new Employee();
	private Lists lists;

	public SUw(Employee employee, Lists lists) {

		this.employee = employee;
		this.lists = lists;

		// UW
		UWMain();
	}

	public void UWMain() {
		boolean check = false;
		while (!check) {
			System.out.println("---------------UW---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1. 인수 정책 수립");
			System.out.println("2. 인수 정책 확인");
			System.out.println("3. 인수 심사 진행");
			int select = Util.IntReader("4. 로그아웃");

			switch (select) {
			case 1:
				addUwRule();
				break;
			case 2:
				getUwRule();
				break;
			case 3:
				checkUW();
				break;
			case 4:
				check = true;
				break;
			default:
				System.out.println("Select Error retry");
				break;
			}
		}
	}
	private void addUwRule() {
		boolean flag = false;
		boolean flag2 = false;
		UwRule uwRule = new UwRule();
		String RTitle = Util.StringReader("인수정책 제목: ");
		if(RTitle.equals("0")) return;
		while(!flag) {
			if(!RTitle.equals("")) {
				flag = true;
				break;
			} else RTitle = Util.StringReader("제목을 입력하지 않으셨습니다.\n제목을 입력해주세요: ");
		}
		uwRule.setTitle(RTitle);
		String RContent = Util.StringReader("인수정책 내용: ");
		if(RContent.equals("0")) return;
		while(!flag2) {
			if(!RContent.equals("")) {
				flag2 = true;
				break;
			} else RContent = Util.StringReader("내용을 입력하지 않으셨습니다.\n내용을 입력해주세요: ");
		}
		uwRule.setContent(RContent);
		uwRule.setEmployeeIdx(this.employee.getEmployeeIdx());
		this.lists.addUwRule(uwRule);
		System.out.println("인수정책 수립이 완료되었습니다.");
	}

	public boolean getUwRule() {
		System.out.println("---------------UWRule LIST---------------");
		List<UwRule> uwRuleList = this.lists.getUwRuleList();
		if (uwRuleList.size() == 0) {
			System.out.println("등록된 인수 정책이 없습니다.");
			return false;
		}
		
		for (UwRule temp : uwRuleList) {
			System.out.println("[정책 번호]");
			System.out.println(temp.getUwRuleIdx());
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
	public boolean checkUW() {
		System.out.println("---------------UW LIST---------------");
		List<Contract> contractList = this.lists.getUwContract();
		if (contractList.size() == 0) {
			System.out.println("인수 검사할 계약이 없습니다.");
			return false;
		}
		System.out.println("[계약번호]   " + "[고객번호]");
		for (Contract temp : contractList) {
			String message = "   " + temp.getContractIdx() + "         " + temp.getUserIdx();
			System.out.println(message);
			System.out.println("[제안서] : " + temp.getSuggestion());
			System.out.println("[청약서] : " + temp.getSubscription());
			System.out.println("-----------------------------------");
		}
		System.out.println("---------------CHECK UW---------------");
		boolean checkInt = false;
		int contractIdx = -1;
		while(!checkInt) {
	         if(contractIdx == -1) {
	        	 contractIdx = Util.IntReader("승인할 계약번호를 입력하세요.");
	         }
	         if(contractIdx != -1) {
	            checkInt = true;
	         }
	      }
		Contract contract = null;
		for (Contract temp : contractList) {
			if (temp.getContractIdx() == contractIdx) {
				contract = temp;
			}
		}
		if(contract == null) {
			System.out.println("해당 계약번호가 존재하지 않습니다.");
			return false;
		}
		int uWChoice = Util.IntReader("승인하시겠습니까? 승인(1) 미승인(2)");
		if (uWChoice == 1) {
			this.lists.modifyCheckUw(contract.getContractIdx());
			System.out.println(contract.getContractIdx() + "번 계약의 인수검사가 성공적으로 수행되었습니다.");
			System.out.println();
			return true;
		}else {
			System.out.println("미승인 처리되었습니다.");
			return false;
		}
	}

	public Lists getLists() {
		return this.lists;
	}
}
