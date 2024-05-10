package MP4_Package;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.*;

public class gatheringGame {
	
	private JFrame frame;
	private static JLabel timeLeft = new JLabel("15");
	public static void changeLabel(String text) {
		timeLeft.setText(text);
	}
	
	int secondsPassed=0;
	private Timer clock;
	private JLabel bush;
	private JLabel berry;
	private int counter=15;
	private int score1=0;
	/**
	 * Create the application.
	 */
	public void createGatheringGUI() {

		System.out.println("Creating gathering minigame");
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(800, 400, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton berryBtn = new JButton("");
		berryBtn.setBounds(285, 240, 50, 60);
		frame.getContentPane().add(berryBtn);
		berryBtn.setOpaque(false);
		berryBtn.setContentAreaFilled(false);
		berryBtn.setBorderPainted(false);
		berryBtn.setVisible(false);
			
		JLabel intructions = new JLabel("Click on as much fruit as you can before the time runs out");
		intructions.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		intructions.setBounds(27, 0, 390, 23);
		frame.getContentPane().add(intructions);

		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.start();
				berryBtn.setVisible(true);
			}
		});

		startBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		startBtn.setBounds(300, 432, 89, 23);
		frame.getContentPane().add(startBtn);
		
		JLabel lblNewLabel = new JLabel("Time Left:");
		lblNewLabel.setBounds(10, 23, 79, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel timeLeft = new JLabel("15");
		timeLeft.setBounds(70, 23, 47, 14);
		frame.getContentPane().add(timeLeft);
		
		berry = new JLabel("");
		berry.setBounds(285, 140, 270, 255);
		frame.getContentPane().add(berry);

		bush = new JLabel("");
		bush.setBackground(new Color(255, 255, 255));
		bush.setBounds(20, 60, 600, 400);
		frame.getContentPane().add(bush);
		
		ImageIcon berryImage = new ImageIcon(this.getClass().getResource("/Assets/blackberry1.png"));
		Image newImg = berryImage.getImage().getScaledInstance(50,63,Image.SCALE_SMOOTH);
		berry.setIcon(new ImageIcon(newImg));

		ImageIcon bushImage = new ImageIcon(this.getClass().getResource("/Assets/bush.jpg"));
		Image newBushImg = bushImage.getImage().getScaledInstance(600,400,Image.SCALE_SMOOTH);
		bush.setIcon(new ImageIcon(newBushImg));

		JLabel lblNewLabel_1 = new JLabel("Score:");
		lblNewLabel_1.setBounds(10, 48, 53, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel score = new JLabel("0");
		score.setBounds(50, 48, 27, 14);
		frame.getContentPane().add(score);
		
		berryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int randomX = rand.nextInt(410)+110;
				int randomY = rand.nextInt(200)+100;
				berryBtn.setBounds(randomX, randomY, 50, 65);
				berry.setBounds(randomX, randomY, 50, 65);
				score1++;
				score.setText(""+score1);
				System.out.println(score1);
			}
		});
		
		clock = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(counter>0) {
				counter =(counter-1);
				timeLeft.setText(""+counter);
				}
				else {
					clock.stop();
					berryBtn.setVisible(false);
					String st = "Yould have collected "+score1+" berries which earns "
							+ 2*score1+" pounds of food";
					JOptionPane.showMessageDialog(null, st);
				}
			}
		});
	}

	public int getFood() {
		return score1*2;
	}	
}
