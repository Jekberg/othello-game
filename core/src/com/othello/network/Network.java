package com.othello.network;

import com.othellog4.game.board.Position;

public class Network {

	private Connection c;
	private boolean host;
	
	public Network(String connection) {
		setup(connection);
		
		
	}
	
	/**
	 * 
	 * @param input Expecting a host!:STRING or an ip
	 */
	private void setup(String input) {
		String[] name;
		input = input.toLowerCase();
		if(input.startsWith("host")) {
			host = true;
			name = input.split("!:");
			c = new HostConnection(name[1]);
		} else {
			c = new Connection(input);
			host = false;
		}
	}
	
	public boolean isOn() {
		return true;
		//return c.isOn();
	}
	
	public boolean isHost() {
		return host;
	}
	
	
	public void sendMove(Position p) {
		String toSend = "12345" + p.col + "," + p.row;
		c.send(toSend);
	}
	
	@SuppressWarnings("deprecation")
	public Position getMove() {
		String temp = c.read();
		temp = temp.substring(5);
		int a,b;
		b = Integer.parseInt(temp.split(",")[0]);
		a = Integer.parseInt(temp.split(",")[1]);
		return (new Position(a,b));
	}

	public void close() {
		c.close();
	}
	
	
}
