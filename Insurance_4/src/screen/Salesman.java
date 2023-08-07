package screen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import contract.Contract;
import dataList.Lists;
import employee.Employee;
import global.Util;
import insurance.Car;
import insurance.Fire;
import insurance.Health;
import insurance.Insurance;
import insurance.Travel;
import user.User;

public class Salesman {

	private Employee employee;
	private Lists lists;
	private int fee;

	public Salesman(Employee employee, Lists lists) {

		this.employee = employee;
		this.lists = lists;
		// SalesMan
		SalesmanMain();
	}

	public void SalesmanMain() {
		boolean check = false;
		while (!check) {
			System.out.println("---------------SalesMan---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1. 제안서/청약서 작성");
			System.out.println("2. 최종 계약 리스트 승인");
			System.out.println("3. 완성 계약 리스트 확인");
			int select = Util.IntReader("4. 로그아웃");

			switch (select) {
			case 1:
				CheckUserList();
				break;
			case 2:
				ShowFinalContract();
				break;
			case 3:
				showCompleteContract();
				break;
			case 4:
				check = true;
				break;
			default:
				System.out.println("Select Error retry");
			}
		}
	}

	// 1번 클릭시
	public void CheckUserList() {
		List<User> userList = this.lists.getUserList();
		User user = null;
		int userIdx = 0;
		System.out.println("---------------User List---------------");
		StringBuilder sb = new StringBuilder();

		if (userList.size() == 0) {
			System.out.println("고객이 존재하지 않습니다.");
			return;
		}
		for (int i = 0; i < userList.size(); i++) {
			User temp = userList.get(i);
			   sb.append("[유저 번호: " + temp.getUserIdx() + "] ").append("[이름: " + temp.getName() + "] ").append("[성별: " + SexToString(temp.isSex()) + "] ")
               .append("[지역: " + temp.getState() + ", " + temp.getCity() + "] ").append("[직업: " + temp.getJob() + "]\n");
      		}
		System.out.print(sb);
		System.out.println("---------------Select---------------");
		// next
		boolean checkUser = false;
		while (!checkUser) {
			userIdx = Util.IntReader("제안서와 청약서를 작성할 고객 번호를 입력해주세요.\n(전으로 돌아가려면, 0을 입력하세요)");
			if (userIdx == 0)
				return;
			for (User temp : userList) {
				if (temp.getUserIdx() == userIdx) {
					checkUser = true;
					user = temp;
				}
			}
			if (user == null)
				System.out.println("존재하지 않는 유저 번호 입니다.");
		}
		// userIdx가 존재하는지도 체크해주는 validation 추가하기
		int temp = -1;
		do {
			int insuranceType = Util.IntReader("제안할 보험 종류를 선택해주세요. 화재(1), 자동차(2), 건강(3), 여행(4) 뒤로가기(5)");
			temp = printInsurace(insuranceType);
		} while (temp == -1);
		if (temp == -3)
			return;
		int insuranceIdx = -1;
		while (true) {
			insuranceIdx = Util.IntReader("보험 번호를 입력해주세요.");
			if (checkInsuranceIdx(insuranceIdx)) {
				break;
			}
			System.out.println("보험 번호를 다시 입력해주세요.");
		}
		String suggestion = Util.StringReader("제안서 내용을 입력해주세요.");
		String subscription = Util.StringReader("청약서 내용을 입력해주세요.");

		Contract contract = new Contract();
		contract.setUserIdx((long) userIdx);
		contract.setSuggestion(suggestion);
		contract.setSubscription(subscription);
		contract.setInsuranceIdx((long) insuranceIdx);
		contract.setFee(fee);
		contract.setEmployeeIdx(employee.getEmployeeIdx());
		contract.setCreated(LocalDate.now());
		this.lists.addContract(contract);
		System.out.println(userIdx + "번 고객의 제안서 청약서 작성이 전송되었습니다.");
	}
	private boolean checkInsuranceIdx(int insuranceIdx) {
		List<Fire> fireList = this.lists.getFireList();
		for (Fire insurance : fireList) {
			if (insurance.getInsuranceIdx() == insuranceIdx) {
				calculateFire(insurance);
				return true;
			}
		}
		List<Car> carList = this.lists.getCarList();
		for (Car insurance : carList) {
			if (insurance.getInsuranceIdx() == insuranceIdx) {
				calculateCar(insurance);
				return true;
			}
		}
		List<Health> healthList = this.lists.getHealthList();
		for (Health insurance : healthList) {
			if (insurance.getInsuranceIdx() == insuranceIdx) {
				calculateHealth(insurance);
				return true;
			}
		}
		List<Travel> travelList = this.lists.getTravelList();
		for (Travel insurance : travelList) {
			if (insurance.getInsuranceIdx() == insuranceIdx) {
				calculateTravel(insurance);
				return true;
			}
		}
		return false;
	}

	private int printInsurace(int insuranceType) {
		boolean flag = false;
		while (!flag) {
			switch (insuranceType) {
			case 1:
				List<Fire> fireList = this.lists.getFireList();
				if (fireList.size() != 0) {
					for (Insurance insurance : fireList) {
						System.out.println(
								"[보험 번호]: "+ insurance.getInsuranceIdx() + " "+
									"[요율]:"+ insurance.getRate() + "  " + "[금액]: " + insurance.getFee()+ 
									"  [보험 내용]: " + insurance.getContent()+ "  [최대 보상금액]: " + insurance.getMaxReward() +" \n");
					}
					flag = true;
				} else {
					System.out.println("보험이 아직 개발되지 않았습니다.");
					return -1;
				}
				break;
			case 2:
				List<Car> carList = this.lists.getCarList();
				if (carList.size() != 0) {
					for (Insurance insurance : carList) {
						System.out.println(
								"[보험 번호]: "+ insurance.getInsuranceIdx() + " "+
									"[요율]:"+ insurance.getRate() + "  " + "[금액]: " + insurance.getFee()+ 
									"  [보험 내용]: " + insurance.getContent()+ "  [최대 보상금액]: " + insurance.getMaxReward() +" \n");
					}
					flag = true;
				} else {
					System.out.println("보험이 아직 개발되지 않았습니다.");
					return -1;
				}
				break;
			case 3:
				List<Health> healthList = this.lists.getHealthList();
				if (healthList.size() != 0) {
					for (Insurance insurance : healthList) {
						System.out.println(
								"[보험 번호]: "+ insurance.getInsuranceIdx() + " "+
									"[요율]:"+ insurance.getRate() + "  " + "[금액]: " + insurance.getFee()+ 
									"  [보험 내용]: " + insurance.getContent()+ "  [최대 보상금액]: " + insurance.getMaxReward() +" \n");
					}
					flag = true;
				} else {
					System.out.println("보험이 아직 개발되지 않았습니다.");
					return -1;
				}
				break;
			case 4:
				List<Travel> travelList = this.lists.getTravelList();
				if (travelList.size() != 0) {
					for (Insurance insurance : travelList) {
						System.out.println(
								"[보험 번호]: "+ insurance.getInsuranceIdx() + " "+
									"[요율]:"+ insurance.getRate() + "  " + "[금액]: " + insurance.getFee()+ 
									"  [보험 내용]: " + insurance.getContent()+ "  [최대 보상금액]: " + insurance.getMaxReward() +" \n");
					}
					flag = true;
				} else {
					System.out.println("보험이 아직 개발되지 않았습니다.");
					return -1;
				}
				break;
			case 5:
				flag = true;
				return -3;
			default:
				return -1;
			}
		}
		return 1;
	}

	// 2번 클릭
	 public void ShowFinalContract() {
	      System.out.println("---------------Final Contract List---------------");
	      List<Contract> contractList = this.lists.getFinalContract();
	      if (contractList.size() == 0) {
	         System.out.println("인수 검사를 시행한 계약이 아직 존재하지 않습니다.");
	         return;
	      }
	      System.out.println("[계약번호] "+"[고객번호] "+"[직원번호]");
	      for (Contract contract : contractList) {
	         System.out.println(
	               contract.getContractIdx() + "        " + contract.getUserIdx() + "        " + contract.getEmployeeIdx());
	      }
	      boolean checkInt = false;
	      int contractIdx = -1;
	      while(!checkInt) {
	          if(contractIdx == -1) {
	        	  contractIdx = Util.IntReader("최종 승인할 계약 번호를 입력해주세요.");
	          }
	          if(contractIdx != -1) {
	             checkInt = true;
	          }
	       }
	      for (Contract contract : contractList) {
	    	  if (contract.getContractIdx() == contractIdx) {
	    		  this.lists.modifyCompleted((long) contractIdx);
	    	      // 여기서 보험을 가지고와서 rate period fee 가지고오기
	    	      System.out.println("최종 계약 처리되었습니다.");
	    	      return;
				}
		  }
	      System.out.println("입력한 계약번호가 존재하지 않습니다.");
	      // contractIdx validation	     
	      System.out.println();
	   }

	   private void showCompleteContract() {
	      System.out.println("---------------Complete Contract List---------------");
	      List<Contract> contractList = this.lists.getCompleteContract();
	      if (contractList.size() == 0) {
	         System.out.println("최종 계약 완료된 보험 계약이 아직 존재하지 않습니다.");
	         return;
	      }
	      System.out.println("[계약번호] "+"[고객번호] "+"[직원번호]");
	      for (Contract contract : contractList) {
	         System.out.println(
	               contract.getContractIdx() + "        " + contract.getUserIdx() + "        " + contract.getEmployeeIdx());
	      } 
	      System.out.println();
	   }

	public Lists getLists() {
		return this.lists;
	}

	private void calculateTravel(Travel insurance) {
		int rate = insurance.getRate();
		int insuranceFee = insurance.getFee();
		int travelPeriod = insurance.getPeriod();

		if (travelPeriod <= 10) {
			fee = insuranceFee * rate;
		} else if (travelPeriod <= 25) {
			fee = (int) (insuranceFee * rate * 1.6);
		} else if (travelPeriod <= 40) {
			fee = (int) (insuranceFee * rate * 2.2);
		} else {
			fee = (int) (insuranceFee * rate * 2.5);
		}
	}

	private void calculateHealth(Health insurance) {
		int rate = insurance.getRate();
		int insuranceFee = insurance.getFee();
		int healthGrade = insurance.getHealthGrade();

		if (healthGrade == 1) {
			fee = (int) (rate * insuranceFee * 0.3);
		} else if (healthGrade == 2) {
			fee = (int) (rate * insuranceFee);
		} else if (healthGrade == 3) {
			fee = (int) (rate * insuranceFee * 1.3);
		} else if (healthGrade == 4) {
			fee = (int) (rate * insuranceFee * 1.7);
		} else {
			fee = (int) (rate * insuranceFee * 2.1);
		}
	}

	private void calculateCar(Car insurance) {
		int rate = insurance.getRate();
		int insuranceFee = insurance.getFee();
		int accidentRecord = insurance.getAccidentRecord();
		int carCount = insurance.getCarCount();
		int price = insurance.getPrice();

		if (carCount == 1) {
			rate *= 1;
		} else if (carCount == 2) {
			rate *= 1.5;
		} else if (carCount == 3) {
			rate *= 2;
		} else {
			rate *= 3;
		}
		if (price < 10000000)
			rate *= 1.3;
		if (accidentRecord > 3)
			rate *= 1.4;
		fee = (int) (rate * insuranceFee);
	}

	private void calculateFire(Fire insurance) {
		int rate = insurance.getRate();
		int insuranceFee = insurance.getFee();
		int bCnt = insurance.getBuildingCount();
		int price = insurance.getBuildingPrice();
		if (bCnt >= 3)
			rate *= 2;
		if (price >= 2000000) {
			rate *= 1.2;
		}
		fee = (int) (rate * insuranceFee);
	}
	public static String SexToString(boolean sex) {
        if(sex == true) 
           return "여자";
        else 
           return "남자";  
     } 
}
