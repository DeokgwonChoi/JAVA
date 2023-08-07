package screen;

import java.util.List;

import contract.Contract;
import dataList.Lists;
import employee.Employee;
import global.Util;
import user.User;

public class SManager {

	private Employee employee;
	private Lists lists;

	public SManager(Employee employee, Lists lists) {

		this.lists = lists;
		this.employee = employee;

		// Manager 계약 관리자
		ManagerMain();
	}

	public void ManagerMain() {
		boolean check = false;

		while (!check) {
			System.out.println("---------------Manager---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1. 보험금 미납자 관리");
			System.out.println("2. 만기계약 대상자 관리");
			int select = Util.IntReader("3. 로그아웃");
			
			switch(select) {
			case 1:
				ShowPayNotList();
				break;
			case 2:
				almostContractList();
				break;
			case 3:
				check = true;
				System.out.println("로그아웃 되었습니다.");
				break;
			default :
				System.out.println("유효한 번호를 입력해주세요.");
				break;
			}
		}
	}
	private void almostContractList() {
		List<Contract> contractList = this.lists.getAlmostContractList();
		if(contractList.size()== 0) {
			System.out.println("만기계약 대상자가 없습니다."); System.out.println(); return;
		}
		for(Contract contract : contractList) {
			System.out.println("[계약 번호] : " + contract.getContractIdx()  + "  [고객 번호] : " + contract.getUserIdx() + "  [계약 날짜] : " + contract.getCreated());
		}
		boolean flag = false;
		long userIdx = (long) Util.IntReader("만기계약을 알릴 고객 번호를 입력하세요. 뒤로가기(0)");
		while(!flag) {
			if(userIdx == 0) {
				flag = true; break;
			}
			for(Contract contract : contractList) {
				if(contract.getUserIdx() == userIdx) {
					userIdx = contract.getUserIdx();
					flag = true;
					break;
				}
			}
			if(!flag) userIdx = Util.IntReader("존재하지 않는 고객 번호입니다. 다시 입력해주세요. 뒤로가기(0)");
		}
		if(userIdx == 0) return;
		if(Util.IntReader("고객한테 만기계약 알림 이메일을 전송하시겠습니까? 승인(1) 미승인(아무버튼)") == 1) {
			User user = this.lists.getUser(userIdx);
			//이메일 보내는거 짜기
			System.out.println(user.getEmail()+ " 메일 전송을 성공했습니다. ");
		}
	}
	
	private void ShowPayNotList() {
		List<Contract> contractList = this.lists.getCheckPayContract();
		if(contractList.size()== 0) {
			System.out.println("미납자가 없습니다."); System.out.println(); return;
		}
		for(Contract contract : contractList) {
			System.out.println("[계약 번호] : " + contract.getContractIdx()  + "  [고객 번호] : " + contract.getUserIdx() + "  [미납 금액] : " + contract.getFee());
		}
		boolean flag = false;
		long userIdx = (long) Util.IntReader("미납을 알릴 고객 번호를 입력하세요. 뒤로가기(0)");
		while(!flag) {
			if(userIdx == 0) {
				flag = true; break;
			}
			for(Contract contract : contractList) {
				if(contract.getUserIdx() == userIdx) {
					userIdx = contract.getUserIdx();
					flag = true;
					break;
				}
			}
			if(!flag) userIdx = Util.IntReader("존재하지 않는 고객 번호입니다. 다시 입력해주세요. 뒤로가기(0)");
		}
		if(userIdx == 0) return;
		if(Util.IntReader("고객한테 미납 알림 이메일을 전송하시겠습니까? 승인(1) 미승인(아무버튼)") == 1) {
			User user = this.lists.getUser(userIdx);
			//이메일 보내는거 짜기
			System.out.println(user.getEmail()+ " 메일 전송을 성공했습니다. ");
		}
	}

	
	public Lists getLists() {
		return this.lists;
	}
} 