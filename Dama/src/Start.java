import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Start extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame = this;	
	private JButton b1 = new JButton("PLAYER VS COMPUTER");
	private JButton b2 = new JButton("PLAYER 1 VS PLAYER 2");
	private JLabel l1 = new JLabel("");
	private JPanel p1 = new JPanel();

	public Start(){
				
		super("Dama 1.0 - By Fafu, Alex & Silvia");
		setResizable(false);		
		setSize(700,700);
		BackGround bg= new BackGround();
		getContentPane().add(bg);
		setVisible(true);		
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = getSize();		
		setLocation((screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height) / 2);
		setLayout(new BorderLayout());		
		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		p1.setLayout(new GridLayout(3,0));
		p1.setBorder(new EmptyBorder(30, 252, 430, 252) );			
		p1.add(b1);		
		p1.add(l1);		
		p1.add(b2);	
		add(p1,BorderLayout.CENTER);			
		b1.addActionListener(new ListenButton1(frame));
		b2.addActionListener(new ListenButton2(frame));			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		revalidate();
	}
	
	
}


class BackGround extends JPanel {
	 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Image img;

    public BackGround() {
            img = Toolkit.getDefaultToolkit().createImage("images/sfondo.jpg");
            loadImage(img);
    }

    private void loadImage(Image img) {
            try {
                    MediaTracker track = new MediaTracker(this);
                    track.addImage(img, 0);
                    track.waitForID(0);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            
            
    }
    @Override
    protected void paintComponent(Graphics g) {
            setOpaque(false);
            g.drawImage(img, 0, 0, null);
            super.paintComponent(g);
            
    }
}
    


class ListenButton1 implements ActionListener {
	
	private JFrame fr;
	
	public ListenButton1(JFrame fr){
		this.fr = fr;

}
	public void actionPerformed(ActionEvent e) {
		
		fr.setVisible(false);
		fr.dispose();
		@SuppressWarnings("unused")
		Grafica f = new Grafica(new Giocatore(0,true,false),new Giocatore(1,false,true));
	}
}

class ListenButton2 implements ActionListener {
	
	private JFrame fr;
	
	public ListenButton2(JFrame fr){
		this.fr = fr;
		
}
	public void actionPerformed(ActionEvent e) {
		fr.setVisible(false);
		fr.dispose();
		@SuppressWarnings("unused")
		Grafica f = new Grafica(new Giocatore(0,true,false),new Giocatore(1,true,false));
		
	}
}


