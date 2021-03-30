package multithreading;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame1 extends JFrame {
	// Константы, задающие размер окна приложения, если оно
	// не распахнуто на весь экран
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	
	private JTextField Frictiontext; 
	
	private JMenuItem pauseMenuItem;
	private JMenuItem resumeMenuItem;
    private JMenuItem frictionMenuItem;
    private int stateFriction = 0;
	// Поле, по которому прыгают мячи
	private Field field = new Field();
	// Конструктор главного окна приложения
	
	public MainFrame1() {
	super("Программирование и синхронизация потоков");
	setSize(WIDTH, HEIGHT);
	Toolkit kit = Toolkit.getDefaultToolkit();
	// Отцентрировать окно приложения на экране
	setLocation((kit.getScreenSize().width - WIDTH)/2,
	(kit.getScreenSize().height - HEIGHT)/2);
	// Установить начальное состояние окна развѐрнутым на весь экран
	
	setExtendedState(MAXIMIZED_BOTH);
	// Создать меню
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	JMenu ballMenu = new JMenu("Мячи");
	Action addBallAction = new AbstractAction("Добавить мяч") {
	public void actionPerformed(ActionEvent event) {
	field.addBall();
	if (!pauseMenuItem.isEnabled() &&
	!resumeMenuItem.isEnabled()) {
	// Ни один из пунктов меню не являются
	// доступными - сделать доступным "Паузу"
	pauseMenuItem.setEnabled(true);
	}
	}
	};
	menuBar.add(ballMenu);
	ballMenu.add(addBallAction);
	JMenu controlMenu = new JMenu("Управление");
	menuBar.add(controlMenu);
	Action pauseAction = new AbstractAction("Приостановить движение"){
	public void actionPerformed(ActionEvent event) {
	field.pause();
	pauseMenuItem.setEnabled(false);
	resumeMenuItem.setEnabled(true);
	}
	};
	pauseMenuItem = controlMenu.add(pauseAction);
	pauseMenuItem.setEnabled(false);
	
	Action resumeAction = new AbstractAction("Возобновить движение") {
	public void actionPerformed(ActionEvent event) {
	field.resume();
	pauseMenuItem.setEnabled(true);
	resumeMenuItem.setEnabled(false);
	}
	};
	resumeMenuItem = controlMenu.add(resumeAction);
	resumeMenuItem.setEnabled(false);
	
	 Action fricAction = new AbstractAction("Трение") {
         public void actionPerformed(ActionEvent e) {
             JPanel p = new JPanel();
             JTextField inputField = new JTextField(10);
             p.add(inputField);
             JOptionPane.showConfirmDialog((Component)null, p, "Коэфицент трения: ", 2);
             MainFrame1.this.field.setStateFriction(Integer.parseInt(inputField.getText()));
         }
     };
     this.frictionMenuItem = controlMenu.add(fricAction);
     this.frictionMenuItem.setEnabled(true);
     this.getContentPane().add(this.field, "Center");
	}
	// Главный метод приложения
	public static void main(String[] args) {
		// Создать и сделать видимым главное окно приложения
		MainFrame1 frame = new MainFrame1();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
