package com.othellog4.game.extension;

import java.util.HashMap;
import java.util.Map;

import com.othellog4.game.GameEvent;
import com.othellog4.game.GameManager;
import com.othellog4.game.board.Piece;
import com.othellog4.game.command.GameCommand;
/**
 * Models a timer to calculate how long a game lasts in total. 
 * <br>
 * Provides the functionality for:
 * <ul>
 * 	<li>Advance the timer</li>
 * 	<li>Suspend the timer</li>
 * 	<li>Stop the timer</li>
 * </ul>
 * 
 * @author John Berg
 * @since 16/02/2018
 * @version 10/03/2018
 */
public class Timer extends GameExtension
{
	//=========================================================================
	//Fields.
	/**
	 * This holds the starting time of the timer. It also visible across
	 * multiple threads
	 */
	private volatile long timeStart;
	/**
	 * This holds the current Piece
	 */
	private Piece current;
	/**
	 * This structure tracks the time, and holds a Piece and the time
	 */
	private volatile Map<Piece, Long> timeTracker;
	//=========================================================================
	//Constructors.
	/**
	 * Initialises the start time, and tracks
	 * {@link com.othellog4.game.board.Piece.Piece_A} and 
	 * {@link com.othellog4.game.board.Piece.Piece_B}
	 */
	public Timer()
	{
		timeStart = 0;
		current = null;
		timeTracker = new HashMap<>();
		timeTracker.put(Piece.PIECE_A, 0L);
		timeTracker.put(Piece.PIECE_B, 0L);
	}
	/**
	 * Moves the timer onwards. 
	 * @param manager takes a {@link com.othellog4.game.GameManager}
	 */
	private void advance(final GameManager manager)
	{
		final long now = System.currentTimeMillis();
		if(current != null)
			timeTracker.put(
					current,
					timeTracker.get(current) + now - timeStart);
		timeStart = now;
		current = manager.game().getCurrent();
	}
	/**
	 * Suspends the timer. 
	 * @param manager takes a
	 * {@link com.othellog4.game.GameManager}
	 */
	public void suspend(final GameManager manager)
	{
		final long now = System.currentTimeMillis();
		timeTracker.put(current, timeTracker.get(current) + now - timeStart);
		current = null;
	}
	/**
	 * Stops the timer
	 * @param manager takes a
	 * 			{@link com.othellog4.game.GameManager}
	 */
	private void stop(final GameManager manager)
	{
		final long now = System.currentTimeMillis();
		if(current != null)
			timeTracker.put(
					current,
					timeTracker.get(current) + now - timeStart);
		timeStart = 0;
		current = null;
	}
	protected synchronized int realTime()
	{
		return current != null
				? (int) (timeTracker.get(current) - timeStart
						+ System.currentTimeMillis()) / 1000
				: 0;
	}
	//=========================================================================
	//Overriden methods.
	/**
	 * 
	 */
	@Override
	public boolean hasResult()
	{
		return true;
	}
	/**
	 * 
	 */
	@Override
	public synchronized void onEvent(
			final GameEvent event,
			final GameManager manager)
	{
		switch(event)
		{
		case BEGIN:
			advance(manager);
			break;
		case NEXT_TURN:
			advance(manager);
			break;
		case PAUSED:
			suspend(manager);
			break;
		case END:
			stop(manager);
			break;
		}
	}
	/**
	 *
	 */
	@Override
	public final void onCommand(
			final GameCommand command,
			final GameManager manager)
	{
		//UNUSED.
	}
	/**
	 * Get the <code>int</code> which represents the time in seconds which
	 * a specified {@link Piece} object has taken.
	 * 
	 * @param piece The object to get the time of.
	 * @return The <code>int</code> which represents seconds.
	 */
	@Override
	public synchronized int result(Piece piece)
	{
		return (int) (timeTracker.get(piece) / 1000);
	}
}
