package screen;

import java.util.ArrayList;
import java.util.List;

import accident.Accident;
import contract.Contract;
import dataList.Lists;
import global.Util;
import user.User;

public class SUser {

	private User user;
	private Lists lists;

	public SUser(User user, Lists lists) {
		this.lists = new Lists();
		this.user = user;
		// User
		UserMain();
	}

	public void UserMain() {
		boolean check = false;

		System.out.println("---------------USER---------------");
		System.out.println();
		System.out.println(this.user.getName() + "님이 접속했습니다.");
		while (!check) {
			System.out.println("1. 제안서 확인 및 승인");
			System.out.println("2. 청약서 확인 및 승인");
			System.out.println("3. 사고 신고");
			System.out.println("4. 사고 결과 확인 및 소송");
			System.out.println("5. 계약금 납입");
			int select = Util.IntReader("6. 로그아웃");

			switch (select) {
			case 1:
				System.out.println("------제안서를 확인합니다------");
				long idx = -1;
				while (!idxCheck(idx)) {
					idx = CheckSuggestion(user.getUserIdx());
				}
				if (idx != -2) {
					System.out.println(idx + "번의 계약 제안서가 승인되었습니다.");
				}
				break;
			case 2:
				System.out.println("------청약서를 확인합니다------");
				long idx2 = -1;
				while (!idxCheck(idx2)) {
					idx2 = CheckSubscription(user.getUserIdx());
				}
				if (idx2 != -2) {
					System.out.println(idx2 + "번의 계약 청약서가 승인되었습니다.");
				}
				break;
			case 3:
				createAccident();
				break;
			case 4:
				checkAccident();
				break;
			case 5:
				System.out.println("------미납된 계약를 확인합니다------");
				payContract();
				break;
			case 6:
				check = true;
				break;
			default:
				System.out.println("Select Error retry");
				break;
			}
		}
	}

	private void payContract() {
		List<Contract> contractList = this.lists.getCheckPayContract(this.user.getUserIdx());
		if (contractList.size() == 0) {
			System.out.println("납부하지 않은 계약이 없습니다.");
			System.out.println();
			return;
		}
		for (Contract contract : contractList) {
			System.out.println("[계약 번호] : " + contract.getContractIdx() + "  [계약 금액] : " + contract.getFee());
		}
		long contractIdx = -1;
		boolean flag = false;
		contractIdx = (long) Util.IntReader("계약 금액을 납부할 계약 번호를 입력해주세요. 뒤로가기(0)");
		while (!flag) {
			if (contractIdx == 0) {
				flag = true;
				break;
			}
			for (Contract contract : contractList) {
				if (contract.getContractIdx() == contractIdx) {
					contractIdx = contract.getContractIdx();
					flag = true;
					break;
				}
			}
			if (!flag)
				contractIdx = Util.IntReader("존재하지 않는 계약 번호입니다. 다시 입력해주세요. 뒤로가기(0)");
		}
		if (contractIdx == 0)
			return;
		if (Util.IntReader("승인하시겠습니까? 승인(1) 미승인(2)") == 1) {
			this.lists.modifyCheckPay(contractIdx);
			System.out.println(contractIdx + "번 계약의 계약금을 성공적으로 납부했습니다. 감사합니다!");
		} else {
			System.out.println("계약금을 납입을 취소합니다.");
		}
	}

	public long CheckSuggestion(Long userIdx) {
		List<Contract> contractList = this.lists.getUserSugContract(userIdx);
		if (contractList.size() == 0) {
			System.out.println("아직 보험 설계사로부터 받은 제안서가 존재하지 않습니다.");
			System.out.println();
			return -2;
		}
		System.out.println("계약번호  " + "고객번호   " + "제안서");
		for (Contract contract : contractList) {
			String message = contract.getContractIdx() + "      " + contract.getUserIdx() + "       "
					+ contract.getSuggestion();
			System.out.println(message);
		}
		// 여기서부터 제안서에 대한 승인 내용
		Contract contract = null;
		int contractIdx = 0;
		boolean flag = false;
		while (!flag) {
			contractIdx = Util.IntReader("승인하실 계약 번호를 입력해주세요(뒤로가기 0)");
			if (contractIdx == 0) {
				break;
			}
			for (Contract temp : contractList) {
				if (temp.getContractIdx() == contractIdx) {
					contract = temp;
					flag = true;
					break;
				}
			}
			if (contract == null)
				System.out.println("존재하지 않는 계약번호입니다. 다시 입력해주세요");
		}
		if (contractIdx == 0) {
			return -2;
		}
		int suggestionChoice = Util.IntReader("승인하시겠습니까? 승인(1) 미승인(2)");
		if (suggestionChoice == 1) {
			this.lists.modifyCheckSug(contract.getContractIdx());
			return contract.getContractIdx();
		} else if (suggestionChoice == 2) {
			System.out.println("미승인 처리 되었습니다.");
			return -1;
		} else {
			System.out.println("1또는2를 입력해주십시오.");
			return 0;
		}
	}

	public long CheckSubscription(Long userIdx) {
		List<Contract> contractList = this.lists.getUserSubContract(userIdx);
		if (contractList.size() == 0) {
			System.out.println("요청 온 청약서가 존재하지 않습니다.");
			System.out.println();
			return -2;
		}
		String message = null;
		System.out.println("계약번호  " + "고객번호   " + "청약서");
		for (Contract contract : contractList) {
			message = contract.getContractIdx() + "       " + contract.getUserIdx() + "       "
					+ contract.getSubscription();
			System.out.println(message);
		}
		// 여기서부터 청약서에 대한 승인 내용
		Contract contract = null;
		int contractIdx = 0;
		boolean flag = false;
		while (!flag) {
			contractIdx = Util.IntReader("승인하실 계약 번호를 입력해주세요(뒤로가기 0)");
			if (contractIdx == 0) {
				return -2;
			}
			for (Contract temp : contractList) {
				if (temp.getContractIdx() == contractIdx) {
					contract = temp;
					flag = true;
					break;
				}
			}
			if (contract == null)
				System.out.println("존재하지 않는 계약번호입니다. 다시 입력해주세요.");
		}
		int subscriptionChoice = Util.IntReader("승인하시겠습니까? 승인(1) 미승인(2)");
		if (subscriptionChoice == 1) {
			System.out.println("승인 처리 되었습니다.");
			this.lists.modifyCheckSub(contract.getContractIdx());
			return contract.getContractIdx();
		} else if (subscriptionChoice == 2) {
			System.out.println("미승인 처리 되었습니다.");
			return -1;
		} else {
			System.out.println("1또는2를 입력해주십시오.");
			return 0;
		}
	}

	public boolean createAccident() {
		System.out.println("사고 신고 내용을 입력해주세요.");
		Accident accident = new Accident();
		accident.setUserIdx(this.user.getUserIdx());
		boolean flag = false;
		long insuranceIdx = -1;
		while (!flag) {
			int type = Util.IntReader("화재(1), 자동차(2), 생명(3), 여행(4)");
			switch (type) {
			case 1:
				insuranceIdx = this.lists.getUserFireList(this.user.getUserIdx());
				accident.setAccidenttype("Fire");
				flag = true;
				break;
			case 2:
				insuranceIdx = this.lists.getUserCarList(this.user.getUserIdx());
				accident.setAccidenttype("Car");
				flag = true;
				break;
			case 3:
				insuranceIdx = this.lists.getUserHealthList(this.user.getUserIdx());
				accident.setAccidenttype("Health");
				flag = true;
				break;
			case 4:
				insuranceIdx = this.lists.getUserTravelList(this.user.getUserIdx());
				accident.setAccidenttype("Travel");
				flag = true;
				break;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				break;
			}
		}
		if (insuranceIdx == -1) {
			System.out.println("고객님께서 가입한 보험이 존재하지 않아 사고 신고가 불가능합니다.");
			System.out.println();
			return false;
		}
		accident.setContent(Util.StringReader("사고 내용: "));
		boolean checkInt = false;
		int tempDamagePrice = -1;
		while (!checkInt) {
			if (tempDamagePrice == -1) {
				tempDamagePrice = Util.IntReader("피해 금액: ");
			}
			if (tempDamagePrice != -1) {
				checkInt = true;
			}
		}
		accident.setDamagePrice(tempDamagePrice);
		accident.setInsuranceIdx(insuranceIdx);
		// 이 고객으로 부터 가입한 보험 가지고오기
		this.lists.addAccident(accident);
		System.out.println("사고 신고가 성공적으로 접수되었습니다.");
		System.out.println();
		return true;
	}

	public boolean checkAccident() {
		System.out.println("------접수한 사고에 대한 결과입니다------");
		List<String> accidentLists = new ArrayList<>();
		long accidentIdx = 0;

		System.out.println();
		System.out.println("피해 금액 보상안 산정 중인 사고 내역");
		// compensationPRice 가 null 값이 자바에는 0으로 인식
		// 보상금액 처리가 완료된 것은 1로 표시하여 구분
		for (int i = 0; i < this.lists.getAccidentList().size(); i++) {
			if (this.lists.getAccidentList().get(i).getCompensationPrice() > 1
					&& this.lists.getAccidentList().get(i).getUserIdx() == this.user.getUserIdx()) {
				System.out.println("[사고번호] : " + this.lists.getAccidentList().get(i).getAccidentIdx());
				System.out.println("[사고날짜] : " + this.lists.getAccidentList().get(i).getAccidentDate());
				System.out.println("[사고유형] : " + this.lists.getAccidentList().get(i).getAccidenttype());
				System.out.println("[사고내용] : " + this.lists.getAccidentList().get(i).getContent());
				System.out.println("[피해금액] : " + this.lists.getAccidentList().get(i).getDamagePrice());
				System.out.println("----------------------------------------");
			}
		}

		System.out.println();
		System.out.println("처리된 신고 내역");

		for (int i = 0; i < this.lists.getAccidentList().size(); i++) {
			if (this.lists.getAccidentList().get(i).isCheckAccident() == true
					&& this.lists.getAccidentList().get(i).getUserIdx() == this.user.getUserIdx()) {
				System.out.println("[사고번호] : " + this.lists.getAccidentList().get(i).getAccidentIdx());
				System.out.println("[사고날짜] : " + this.lists.getAccidentList().get(i).getAccidentDate());
				System.out.println("[사고유형] : " + this.lists.getAccidentList().get(i).getAccidenttype());
				System.out.println("[사고내용] : " + this.lists.getAccidentList().get(i).getContent());
				System.out.println("[피해금액] : " + this.lists.getAccidentList().get(i).getDamagePrice());
				System.out.println("[보상금액] : " + this.lists.getAccidentList().get(i).getCompensationPrice());
				accidentIdx = this.lists.getAccidentList().get(i).getAccidentIdx();
				if (accidentIdx != 0) {
					if (Util.IntReader("소송하시겠습니까? 소송 1 미소송 2") == 1) {
						this.lists.modifyLawsuitStatus(accidentIdx);
						System.out.println("성공적으로 소송 되었습니다.");
					}
				}
			}
		}

		return true;
	}

	public Lists getLists() {
		return this.lists;
	}

	private boolean idxCheck(long idx) {
		if (idx == -1)
			return false;
		return true;
	}
}