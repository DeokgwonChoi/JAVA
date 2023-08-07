package screen;

import java.util.List;

import accident.Accident;
import dataList.Lists;
import employee.Employee;
import global.Util;

public class SDamageAssessor {

	private Lists lists;
	private Employee employee;

	public SDamageAssessor(Employee employee, Lists lists) {
		this.lists = lists;
		this.employee = employee;
		// DamageAssessor 손해 관리 직원
		DamageAssessorMain();
	}

	public void DamageAssessorMain() {
		boolean check = false;
		while (!check) {
			System.out.println("---------------DamageAssessor---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1. 승인된 사고 내역 보기");
			System.out.println("2. 사고 손해 배상금 책정");
			int select = Util.IntReader("3. 로그아웃");

			switch (select) {
			case 1:
				showAccidentList();
				break;
			case 2:
				modifyCompensationPrice();
				break;
			case 3:
				check = true;
				break;
			default:
				System.out.println("유효한 입력 값을 입력해주세요.");
				break;
			}
		}
	}

	private void modifyCompensationPrice() {
		List<Accident> accidentList = this.lists.getAccidentNotCompensation();
		if (accidentList.size() == 0) {
			System.out.println("손해 배상금을 책정 할 사고 내역이 없습니다.");
			return;
		}
		Accident accident = null;
		int accidentIdx;
		boolean flag = false;
		accidentIdx = Util.IntReader("금액을 책정할 사고 번호를 입력해주세요.(뒤로가기 0)");
		while (!flag) {
			if (accidentIdx == 0) {
				flag = true;
				break;
			}
			for (Accident temp : accidentList) {
				if (temp.getAccidentIdx() == accidentIdx) {
					accident = temp;
					flag = true;
					break;
				}
			}
			if (!flag) {
				accidentIdx = Util.IntReader("존재하지 않는 사고 번호입니다. 다시 입력해주세요.(뒤로가기 0)");
			}
		}
		if (accidentIdx == 0)
			return;
		// 사고 내용을 보여주고
		System.out.println("[사고번호] : " + accident.getAccidentIdx());
		System.out.println("[사고유형] : " + accident.getAccidenttype());
		System.out.println("[사고내용] : " + accident.getContent());
		System.out.println("[피해금액] : " + accident.getDamagePrice());
		System.out.println();

		boolean checkInt = false;
		int price = -1;
		while (!checkInt) {
			if (price == -1) {
				price = Util.IntReader("책정한 보상 금액을 입력해주세요.");
			}
			if (price != -1) {
				checkInt = true;
			}
		}

		if (this.lists.modifyCompensationPrice(accident.getAccidentIdx(), price)) {
			System.out.println(accident.getAccidentIdx() + "번의 사고 손해 금액 책정이 성공적으로 이루어졌습니다.");
			System.out.println();
		}

	}

	public void showAccidentList() {
		List<Accident> list = this.lists.getAccidentNotCompensation();
		if (list.size() == 0) {
			System.out.println("접수된 사고 내역이 존재하지 않습니다.");
			return;
		}
		System.out.println("----------------------------------");
		for (Accident accident : list) {
			System.out.println("[사고번호] : " + accident.getAccidentIdx());
			System.out.println("[사고유형] : " + accident.getAccidenttype());
			System.out.println("----------------------------------");

		}
	}

	public Lists getLists() {
		return this.lists;
	}

}
