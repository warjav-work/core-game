package engine.events;

import engine.render.core.Window;
import engine.render.graphics.gui.Button;

public class ButtonEvent {
	private Button source;
	private Window win;
	
	public ButtonEvent(Button source) {
		this.source = source;
		this.win = Window.getWindow();
	}
	
	public Window getWindow() {
		return win;
	}
	
	public Button getSource() {
		return source;
	}

}
