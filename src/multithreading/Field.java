package multithreading;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;



public class Field extends JPanel {
	// ���� ������������������ ��������
		private boolean paused;
		private int friction = 0;
		// ������������ ������ �������� �����
		private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
		// ����� ������ �������� �� ���������� ��������� ������� ActionEvent
		// ��� �������� ��� ���������� ������������ ��������� �����,
		// ����������� ��������� ActionListener
		private Timer repaintTimer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		// ������ ����������� ������� ActionEvent - ����������� ����
		repaint();
		}
		});
		// ����������� ������ BouncingBall
		public Field() {
		// ���������� ���� ������� ���� �����
		setBackground(Color.WHITE);
		// ��������� ������
		repaintTimer.start();
		}
		// �������������� �� JPanel ����� ����������� ����������
		public void paintComponent(Graphics g) {
		// ������� ������ ������, �������������� �� ������
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		 Iterator var3 = this.balls.iterator();

	        while(var3.hasNext()) {
	        	BouncingBall ball = (BouncingBall)var3.next();
	            ball.paint(canvas);
	            ball.setStateFriction(this.friction);
		}
		}
		// ����� ���������� ������ ���� � ������
		public void addBall() {
		//����������� � ���������� � ������ ������ ���������� BouncingBall
		// ��� ������������� ���������, ��������, �������, �����
		// BouncingBall ��������� ��� � ������������
		balls.add(new BouncingBall(this));
		}
		// ����� ������������������, �.�. ������ ���� ����� �����
		// ������������ ���� ������
		public synchronized void pause() {
		// �������� ����� �����
		paused = true;
		}
		// ����� ������������������, �.�. ������ ���� ����� �����
		// ������������ ���� ������
		public synchronized void resume() {
		// ��������� ����� �����
		paused = false;
		// ����� ��� ��������� ����������� ������
		notifyAll();
		}
		
		public void stopBall() throws InterruptedException {
		        this.wait();
		    }
		 
		public void setStateFriction(int fric) {
	        this.friction = fric;
	    }
	
		
		// ������������������ ����� ��������, ����� �� ��� ���������
		// (�� ������� �� ����� �����?)
		public synchronized void canMove(BouncingBall ball) throws InterruptedException {
		if (paused) {
		// ���� ����� ����� �������, �� �����, ��������
		// ������ ������� ������, ��������
		wait(); }
		
		}
		
}
