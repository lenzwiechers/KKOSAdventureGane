import java.awt.geom.Line2D;

public class Enemy extends GameObject {

	private static final long serialVersionUID = 4573246985108449527L;

	private boolean inWall;

	private boolean playerContact;

	public static int x1, x2, y1, y2;

	Line2D[] l = new Line2D[3];

	public boolean isInScreen = false;

	Line2D line;
	
	private boolean right = true;
	private boolean left;

	public Enemy(String picName, ObjectHandler handler) {

		super(picName, handler);

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.posX = 350;
		this.posY = 300;
		this.width = 100;
		this.height = 100;

		this.velX = 0.0000001f;

		if (picName == "gollum") {
			this.velX = 0.0000004f;
		} else if (picName == "chonker") {
			this.velX = 0.0000001f;
		}

		for (int i = 0; i < l.length; i++) {
			l[i] = new Line2D.Float();
		}

	}

	public Enemy(int posX, int posY, ObjectHandler handler) {
		super("item_t", handler);

		this.velX = 0.0000001f;
		this.velY = 0.00000025f;

		this.posX = posX;
		this.posY = posY;
		this.width = 99;
		this.height = 99;

		/*
		 * if (picName == "gollum") { this.velX = 0.0000004f; } else if (picName ==
		 * "chonker") { this.velX = 0.0000001f; }
		 */

		for (int i = 0; i < l.length; i++) {
			l[i] = new Line2D.Float();
		}

		line = new Line2D.Float();
	}

	public void tick(long dt) {

		l[0].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + 1);
		l[1].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + (handler.player.get(0).getSize('y') / 2));
		l[2].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + handler.player.get(0).getSize('y') - 1);

		if (checkContact()) {
			if (posX < handler.player.get(0).getPos('x')) {
				posX += velX * dt;
				while (wallCollision()) {
					posX -= 1;
				}
				right = true;
				left = false;
			} else if (posX > handler.player.get(0).getPos('x')) {
				posX -= velX * dt;
				while (wallCollision()) {
					posX += 1;
				}
				right = false;
				left = true;
			}
		} else if (right) {
			line.setLine(posX + width + 1, posY + height + 1, posX + width + 2, posY + height + 1);
			for(int i = 0; i < handler.waende.size(); i++) {
				if(!(line.intersects(handler.waende.get(i).getBounds()))) {
					posX += (velX * dt) / 2;
					while (wallCollision()) {
						posX -= 1;
					}
				}
			}
			
		} /*else if (left) {
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
			}
		}*/

		posY += velY * dt;
		inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			this.posY--;
		}
		if (inWall) {
			this.velY = 0;
		}

		if (onWall()) {
			this.velY = 0;
		}

		addGravity();
	}

	public boolean checkContact() {

		for (int j = 0; j < l.length; j++) {
			playerContact = true;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (l[j].intersects(handler.waende.get(i).getBounds())) {
					playerContact = false;
				}
			}
			if (playerContact) {
				return true;
			}
		}
		return false;
	}

}
