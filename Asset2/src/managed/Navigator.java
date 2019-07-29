package managed;

import java.util.Stack;

public class Navigator {
	private Stack<String> stack = new Stack<>();

	public String go(String paginaCorrente, String paginaDestinazione) {
		stack.push(paginaCorrente);
		return paginaDestinazione;

	}

	public String back() {
		return stack.pop();
	}

}
