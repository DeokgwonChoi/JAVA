import java.util.Random;

public class Move {
	private int data[][];
	private int temp = 0;
	public Move(){
		data = new int[4][4];
	}
	public void setinit() {
		for(int i = 0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			data[i][j] = 0;
    		}
    	}
	}
	public void up() {

		for(int i=0; i<4; i++) {
			if(data[2][i] == data[3][i] ) {
				data[2][i] = data[2][i] + data[3][i];
				data[3][i] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[2][i] == 0 ) {
				data[2][i] = data[3][i];
				data[3][i] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[1][i] == data[2][i] ) {
				data[1][i] = data[1][i] + data[2][i];
				data[2][i] = data[3][i];
			}
		}
		for(int i=0; i<4; i++) {
			if(data[1][i] == 0 ) {
				data[1][i] = data[2][i];
				data[2][i] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[0][i] == data[1][i] ) {
				data[0][i] = data[0][i] + data[1][i];
				data[1][i] = data[2][i];
			}
		}
		for(int i=0; i<4; i++) {
			if(data[0][i] == 0 ) {
				data[0][i] = data[1][i];
				data[1][i] = 0;
			}
		}
		randomAdd();
	}
	public void down() {

		for(int i=0; i<4; i++) {
			if(data[1][i] == data[0][i] ) {
				data[1][i] = data[1][i] + data[0][i];
				data[0][i] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[1][i] == 0 ) {
				data[1][i] = data[0][i];
				data[0][i] = 0;
			}
		}
		randomAdd();
	}
	public void left() {

		for(int i=0; i<4; i++) {
			if(data[i][2] == data[i][3] ) {
				data[i][2] = data[i][2] + data[i][3];
				data[i][3] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[i][2] == 0 ) {
				data[i][2] = data[i][3];
				data[i][3] = 0;
			}
		}
		randomAdd();
	}
	public void right() {

		for(int i=0; i<4; i++) {
			if(data[i][1] == data[i][0] ) {
				data[i][1] = data[i][1] + data[i][0];
				data[i][0] = 0;
			}
		}
		for(int i=0; i<4; i++) {
			if(data[i][1] == 0 ) {
				data[i][1] = data[i][0];
				data[i][0] = 0;
			}
		}
		randomAdd();
	}
	public int randomAdd(){
		Random random = new Random();
		isFull();
		int i = (int)(Math.random() * 100);
		if(i < 6) {
			if(data[0][0] == 0) {
				data[0][0]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(6 <= i && i < 12) {
			if(data[0][1] == 0) {
				data[0][1]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(12 <= i && i < 18) {
			if(data[0][2] == 0) {
				data[0][2]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(18 <= i && i < 25) {
			if(data[0][3] == 0) {
				data[0][3]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(25 <= i && i < 31) {
			if(data[1][0] == 0) {
				data[1][0]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(31 <= i && i < 37) {
			if(data[1][1] == 0) {
				data[1][1]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(37 <= i && i < 43) {
			if(data[1][2] == 0) {
				data[1][2]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(43 <= i && i < 50) {
			if(data[1][3] == 0) {
				data[1][3]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(50 <= i && i < 56) {
			if(data[2][0] == 0) {
				data[2][0]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(56 <= i && i < 62) {
			if(data[2][1] == 0) {
				data[2][1]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(62 <= i && i < 68) {
			if(data[2][2] == 0) {
				data[2][2]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(68 <= i && i < 75) {
			if(data[2][3] == 0) {
				data[2][3]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(75 <= i && i < 81) {
			if(data[3][0] == 0) {
				data[3][0]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(81 <= i && i < 87) {
			if(data[3][1] == 0) {
				data[3][1]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(87 <= i && i < 93) {
			if(data[3][2] == 0) {
				data[3][2]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}else if(93 <= i && i < 100) {
			if(data[3][3] == 0) {
				data[3][3]= (random.nextDouble() < 0.9) ? 2 : 4;
				return -1;
			}			
		}		
		return randomAdd();
	}
	private void isFull() {
		for(int i = 0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			if(data[i][j]==0) {
    				temp = 1;
    			}
    		}
    	}	
		if (this.temp==0) {
			System.exit(0);
		}
		this.temp = 0;
	}

	public String getValue(int i, int j) {
		return String.valueOf(data[i][j]);
	}
}
