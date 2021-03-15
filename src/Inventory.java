import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Inventory extends Window {

	private static final long serialVersionUID = -8220570650027588844L;

	static JPanel panel = new JPanel();

	Picture background;

	JButton button;
	JButton f;

	Image img;

	Window window;

	Item[][] items = new Item[5][5];

	JButton[][] itemButtons = new JButton[5][5];

	public Inventory(Window window) {
		super("Inventory", 0, 0, 500, 500, panel);

		/*
		 * for (int i = 0; i < 5; i ++) { for (int e = 0; e < 5; e ++) { items[i][e] =
		 * new Item(new ObjectHandler()); items[i][e].setVisible(true); } }
		 */
		try {

			this.setVisible(false);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			this.window = window;

			background = new Picture("inv_background");

			panel.setLayout(null);

			panel.add(background);
			background.setBounds(0, 0, 1000, 1000);
			img = ImageIO.read(new File("assets/sword.png"));
			/*for (int i = 0; i < items.length; i++) {
				for (int j = 0; j < items.length; j++) {
					itemButtons[i][j] = new JButton(new ImageIcon(img));
					panel.add(itemButtons[i][j]);
					itemButtons[i][j].setBounds(20 + i * 70, 20 + j * 70, 50, 50);
					itemButtons[i][j].setVisible(true);
				}
			}
			*/
			button = new JButton("Go back");
			panel.add(button);
			button.setBounds(20, 400, 330, 20);
			button.setVisible(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					discardInv();
				}
			});
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void showInv() {
		this.setVisible(true);
		window.setVisible(false);
	}

	public void discardInv() {
		this.setVisible(false);
		window.setVisible(true);
	}

	public void addItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (items[i][j] == null) {
					items[i][j] = item;
					break;
				}
			}
		}
	}
}
