package screen;

import java.util.List;

import dataList.Lists;
import employee.Employee;
import global.Util;
import insurance.Car;
import insurance.Fire;
import insurance.Health;
import insurance.Insurance;
import insurance.Travel;

public class SDesigner {

	private Employee employee;
	private Lists lists;

	public SDesigner(Employee employee, Lists lists) {
		this.employee = employee;
		this.lists = lists;

		// Designer 상품 개발자
		DesignerMain();
	}

	public void DesignerMain() {
		boolean check = false;
		while (!check) {
			System.out.println("---------------Designer---------------");
			System.out.println();
			System.out.println(this.employee.getName() + "님이 접속했습니다.");
			System.out.println("1. 화재 보험 개발");
			System.out.println("2. 실손 의료비 보험 개발");
			System.out.println("3. 자동차 보험 개발");
			System.out.println("4. 여행 보험 개발");
			int select = Util.IntReader("5. 로그아웃");
			switch(select) {
			case 1:
				addFireInsurance();
				break;
			case 2:
				addHealthInsurance();
				break;
			case 3:
				addCarInsurance();
				break;
			case 4:
				addTravelInsurance();
				break;
			case 5:
				check = true;
				break;
			default:
				System.out.println("Select Error retry");
				break;
			}
		}
	}

	private void addTravelInsurance() {
		Travel travel = new Travel();
		travel.setContent(Util.StringReader("내용: "));
		travel.setFee(Util.IntReader("요금: "));
		travel.setMaxReward(Util.IntReader("최대 보상금: "));
		travel.setPeriod(Util.IntReader("기간: "));
		travel.setRate(Util.IntReader("요율: "));
		travel.setEmployeeIdx(this.employee.getEmployeeIdx());
		travel.setCountry(Util.StringReader("여행가는 국가"));
		travel.setPeriod(Util.IntReader("여행 기간"));
		this.lists.addTravelInsurance(travel);
	}

	private void addCarInsurance() {
		Car car = new Car();
		car.setContent(Util.StringReader("내용: "));
		car.setFee(Util.IntReader("요금: "));
		car.setMaxReward(Util.IntReader("최대 보상금: "));
		car.setPeriod(Util.IntReader("기간: "));
		car.setRate(Util.IntReader("요율: "));
		car.setEmployeeIdx(this.employee.getEmployeeIdx());
		car.setAccidentRecord(Util.IntReader("사고 횟수: "));
		car.setCarCount(Util.IntReader("보유 차량 수: "));
		car.setCarNo(Util.StringReader("자동차 번호 "));
		car.setPrice(Util.IntReader("자동차 가격 "));
		this.lists.addCarInsurance(car);
	}

	private void addHealthInsurance() {
		Health health = new Health();
		health.setContent(Util.StringReader("내용: "));
		health.setFee(Util.IntReader("요금: "));
		health.setMaxReward(Util.IntReader("최대 보상금: "));
		health.setPeriod(Util.IntReader("기간: "));
		health.setRate(Util.IntReader("요율: "));
		health.setEmployeeIdx(this.employee.getEmployeeIdx());
		health.setHealthGrade(Util.IntReader("건강 등급 : "));
		this.lists.addHealthInsurance(health);
	}

	private void addFireInsurance() {
		Fire fire = new Fire();
		fire.setContent(Util.StringReader("내용: "));
		fire.setFee(Util.IntReader("요금: "));
		fire.setMaxReward(Util.IntReader("최대 보상금: "));
		fire.setPeriod(Util.IntReader("기간: "));
		fire.setRate(1);
		fire.setEmployeeIdx(this.employee.getEmployeeIdx());
		//fire 부분
		fire.setBuildingCount(Util.IntReader("건물개수: "));
		fire.setBuildingPrice(Util.IntReader("건물가격: "));
		this.lists.addFireInsurance(fire);
	}


	public Lists getLists() {
		return this.lists;
	}
}