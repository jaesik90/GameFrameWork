/*
 * ��� ������ �� �гξȿ��� �׷��� ������!!
 * �ƹ��� ������ ����� �پ��ϴ���, �г���
 * ���� 1���� ���ȴ�!!!
 * 
 * ��� ������Ʈ�� �ᱹ �� �гο� �׷�����
 * �ϹǷ�, �� �г��� paint �޼����� �μ��� ����
 * �Ǵ� Graphics ��ü�� ���ӿ� ������ ��� ����
 * ��Ʈ�� ���޹޾ƾ� �Ѵ�...
 * */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	public static final int WIDTH=400;
	public static final int HEIGHT=300;
	public static final int SCALE=3;
	boolean flag = true; //���� ���� ���θ� �����ϴ� ����
	Thread thread; //���� � ������
	Player player;
	ObjectManager objectManager;//��ü��� ������
	
	
	public GamePanel(){
		thread = new Thread(this);
		thread.start();
		
		init();
		
		//ũ�� ����
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
	}
	
	public void init(){
		//��� ������ ����
		objectManager =new ObjectManager();
		
		//���ΰ� ���� ��Ű��
		player =new Player(objectManager,ObjectId.Player, 100, 200, 50, 50);
		objectManager.addObject(player);//1�� �߰�
		
		//���� ����!!
		Random r = new Random();
		for(int i=0; i<10; i++){
		int x  = r.nextInt((WIDTH*SCALE+500)-(WIDTH*SCALE+1));
		int y  = r.nextInt((HEIGHT*SCALE-50+1))+50;
		
		Enemy enemy = new Enemy(objectManager, ObjectId.Enemy, x, y, 30, 30);
		objectManager.addObject(enemy);
		}
		//�гΰ� Ű���� ������ ����
		this.addKeyListener(new KeyBoard(player));
		
	}
	
	protected void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH*SCALE,  HEIGHT*SCALE);
			
			for(int i=0; i<objectManager.list.size();i++){
				GameObject obj=objectManager.list.get(i);
				obj.render(g);
			}
	}

	public void run() {
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//������Ʈ �Ŵ����� ��ϵ� ��� ~~��ü����
			//������� tick()�� ȣ���غ���!!
			for(int i=0; i<objectManager.list.size();i++){
				GameObject obj=objectManager.list.get(i);
				obj.tick();
			}
			//player.tick();
			
			repaint();//paintComponent�� ����ȣ��
			//�Ѿ��� tick, render
			//������ tick, render
			//�������� tick, render....
			//��� ���� �� ��Ƶ� �� �ִ� ��ü�� �Ѱ��� �ʿ��ϴ�...
		}
	}
}
