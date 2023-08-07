package global;

public class Exceptions extends Exception{
	private static final long serialVersionUID = 1L;
	Exceptions(String errorMessage){
		//�ش� ������ �߻��Ͽ����� �����޽����� ����Ѵ�.
		super(errorMessage);
	}	
}