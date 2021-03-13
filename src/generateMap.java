import java.util.LinkedList;

import javax.swing.JPanel;

public abstract class generateMap {
	static LinkedList<Wand> waende = new LinkedList<Wand>();
	static LinkedList<Door> tueren = new LinkedList<Door>();
	static LinkedList<Item> items = new LinkedList<Item>();

	public static void generate(JPanel panel, ObjectHandler handler) {
		generateStartRoom(handler);

		for (int i = 0; i < waende.size(); i++) {
			handler.addObject(waende.get(i));
		}
		for (int i = 0; i < tueren.size(); i++) {
			handler.addObject(tueren.get(i));
		}
		for (int i = 0; i < items.size(); i++) {
			handler.addObject(items.get(i));
		}
	}

	public static void generateStartRoom(ObjectHandler handler) {
		waende.add(new Wand(0, 0, 100, 700, handler));
		waende.add(new Wand(0, 700, 1000, 100, handler));
		waende.add(new Wand(1000, 700, 1000, 100, handler));
		waende.add(new Wand(1900, 0, 100, 700, handler));
		waende.add(new Wand(200, 500, 100, 100, handler));
		waende.add(new Wand(400, 400, 400, 100, handler));
		waende.add(new Wand(1000, 0, 100, 600, handler));
		waende.add(new Wand(900, 200, 100, 100, handler));
		waende.add(new Wand(1100, 500, 200, 100, handler));
		waende.add(new Wand(1100, 300, 100, 100, handler));
		waende.add(new Wand(1700, 400, 200, 100, handler));
		waende.add(new Wand(1400, 200, 100, 0, handler));

		items.add(new Item(1000, -100, handler));

		Door door1 = new Door(800, 600, 100, 100, handler);
		Door door2 = new Door(600, 300, 100, 100, handler);

		tueren.add(door1);
		tueren.add(door2);
		door1.connectExit(door2);

		Door door3 = new Door(1800, 300, 100, 100, handler);
		
		tueren.add(door3);
		
		
		
		
		
		
	}
}