import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGridUI extends JFrame {
    /**
	 * 
	 */
	Move move = new Move();
	
	
	private static final long serialVersionUID = 1L;
	private JButton[][] numberButtons;

    public NumberGridUI() {
        super("Number Grid with Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 4x4 크기의 버튼 배열 생성
        numberButtons = new JButton[4][4];
        
        // 랜덤한 숫자로 버튼 초기화
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {             
                numberButtons[row][col] = new JButton();
                numberButtons[row][col].setPreferredSize(new Dimension(100, 50));
            }
        }
      

        // 전체 프레임의 레이아웃 설정
        setLayout(new BorderLayout());

        // 동, 서, 남, 북에 버튼 추가
        JButton eastButton = new JButton("EAST");
        JButton westButton = new JButton("WEST");
        JButton southButton = new JButton("SOUTH");
        JButton northButton = new JButton("NORTH");

        add(eastButton, BorderLayout.EAST);
        add(westButton, BorderLayout.WEST);
        add(southButton, BorderLayout.SOUTH);
        add(northButton, BorderLayout.NORTH);
        
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move.up();
                updateNumbers();
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move.down();
                updateNumbers();
            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move.left();
                updateNumbers();
            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move.right();
                updateNumbers();
            }
        });

        // 중앙에 4x4 스크린 추가
        JPanel centerPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                centerPanel.add(numberButtons[row][col]);
            }
        }

        add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // 화면 중앙에 UI 표시
        setVisible(true);
        move.setinit();
    }
    private void updateNumbers() {
    	for(int i = 0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			numberButtons[i][j].setText(move.getValue(i,j));
    		}
    	}    	 
    }
    
}