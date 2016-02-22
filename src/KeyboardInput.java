import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyboardInput extends JFrame {

	boolean q, w, e,r,t,z,u,i,o,p,a,s,d,f,g,h,j,k,l,y;
	boolean error;

	public KeyboardInput() {
		this.setSize(new Dimension(100, 100));
		this.setVisible(true);

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				

			}

			@Override
			public void keyReleased(KeyEvent event) {
				
				System.out.println(getStateAsString());
				switch (event.getKeyCode()) {
				case KeyEvent.VK_Q:
					q = false;
					break;
				case KeyEvent.VK_W:
					w = false;
					break;
				case KeyEvent.VK_E:
					e = false;
					break;
				case KeyEvent.VK_R:
					r = false;
					break;
				case KeyEvent.VK_T:
					t = false;
					break;
				case KeyEvent.VK_Z:
					z = false;
					break;
				case KeyEvent.VK_U:
					u = false;
					break;
				case KeyEvent.VK_I:
					i = false;
					break;
				case KeyEvent.VK_O:
					o = false;
					break;
				case KeyEvent.VK_P:
					p = false;
					break;
				case KeyEvent.VK_A:
					a = false;
					break;
				case KeyEvent.VK_S:
					s = false;
					break;
				case KeyEvent.VK_D:
					d = false;
					break;
				case KeyEvent.VK_F:
					f = false;
					break;
				case KeyEvent.VK_G:
					g = false;
					break;
				case KeyEvent.VK_H:
					h = false;
					break;
				case KeyEvent.VK_J:
					j = false;
					break;
				case KeyEvent.VK_K:
					k = false;
					break;
				case KeyEvent.VK_L:
					l = false;
					break;
				case KeyEvent.VK_Y:
					y = false;
					break;
					
				case KeyEvent.VK_SPACE:
					error = false;
					break;
				default:
					break;
				}
				
			}

			@Override
			public void keyPressed(KeyEvent event) {
				switch (event.getKeyCode()) {
				case KeyEvent.VK_Q:
					q = true;
					break;
				case KeyEvent.VK_W:
					w = true;
					break;
				case KeyEvent.VK_E:
					e = true;
					break;
				case KeyEvent.VK_R:
					r = true;
					break;
				case KeyEvent.VK_T:
					t = true;
					break;
				case KeyEvent.VK_Z:
					z = true;
					break;
				case KeyEvent.VK_U:
					u = true;
					break;
				case KeyEvent.VK_I:
					i = true;
					break;
				case KeyEvent.VK_O:
					o = true;
					break;
				case KeyEvent.VK_P:
					p = true;
					break;
				case KeyEvent.VK_A:
					a = true;
					break;
				case KeyEvent.VK_S:
					s = true;
					break;
				case KeyEvent.VK_D:
					d = true;
					break;
				case KeyEvent.VK_F:
					f = true;
					break;
				case KeyEvent.VK_G:
					g = true;
					break;
				case KeyEvent.VK_H:
					h = true;
					break;
				case KeyEvent.VK_J:
					j = true;
					break;
				case KeyEvent.VK_K:
					k = true;
					break;
				case KeyEvent.VK_L:
					l = true;
					break;
				case KeyEvent.VK_Y:
					y = true;
					break;
					
				case KeyEvent.VK_SPACE:
					error = true;
					break;
				default:
					break;
				}

			}
		});
	}
	
	
	public String getStateAsString(){
		String st = "";
		st = appendByBool(st, q);
		st = appendByBool(st, w);
		st = appendByBool(st, e);
		st = appendByBool(st, r);
		st = appendByBool(st, t);
		st = appendByBool(st, z);
		st = appendByBool(st, u);
		st = appendByBool(st, i);
		st = appendByBool(st, o);
		st = appendByBool(st, p);
		st = appendByBool(st, a);
		st = appendByBool(st, s);
		st = appendByBool(st, d);
		st = appendByBool(st, f);
		st = appendByBool(st, g);
		st = appendByBool(st, h);
		st = appendByBool(st, j);
		st = appendByBool(st, k);
		st = appendByBool(st, l);
		st = appendByBool(st, y);
		st = appendByBool(st, error);
		
		
		
		
		return st;
	}
	
	String appendByBool(String s, boolean b){
		if(b){
			return s+"1;";
		}else{
			return s+"0;";
		}
	}
}
