
public class NullDataException extends Exception{
	private static final long serialVersionUID = 1L;
	NullDataException(String errorMessage){
		//해당 에러가 발생하였을때 에러메시지를 출력한다.
		super(errorMessage);
	}	
}
