package com.othellog4.game;

/**
 * 
 * 
 * 
 * @author 	159014260 John Berg
 * @since	25/01/2018
 * @version 25/01/2018
 */
public enum GameEvent
{
	BEGIN,
	PAUSED,
	/**
	 * Signal the end of a game.
	 */
	END,
	/**
	 * Signals the event of the next turn.
	 */
	NEXT_TURN,
}