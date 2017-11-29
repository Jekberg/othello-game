package com.othellog4.game;

import com.othellog4.game.board.BoardView;
import com.othellog4.game.board.GameBoard;
import com.othellog4.game.board.InvalidMoveException;
import com.othellog4.game.board.Piece;
import com.othellog4.game.board.Position;

/**
 * The {@code Game} is a class which models a game of Othello, which has the
 * responsibility of managing and maintaining the flow of the game.
 * 
 * <p>
 * The {@code Game} provides the capability to get information regarding the
 * current {@link Piece} which is to make a move, additionally some methods
 * allow for interaction with the {@link GameBoard} class using the current
 * {@link Piece}.
 * </p>
 * 
 * @author 	159014260 John Berg 
 * @author 	Eastwood
 * @author  Arvinder Chatha
 * @since 	18/10/2017
 * @version 20/11/2017
 * @see GameBoard
 * @see Piece
 */
public class Game
{
	//=========================================================================
	//Fields.
	/**
	 * The {@link GameBoard} of <code>this</code> game.
	 * 
	 * @see GameBoard
	 */
	private final GameBoard board;
	/**
	 * The {@link Piece} object which represents the {@link Piece} of the
	 * current player.
	 * 
	 * @see Piece
	 */
	private Piece current;
	//=========================================================================
	//Constructors.
	/**
	 * Create a {@code Game} object by specifying a {@link GameBoard} that
	 * a {@code Game} should be played on.
	 * 
	 * <p>
	 * The current {@link Piece} will be set to {@link Piece#PIECE_A} as
	 * {@link Piece#PIECE_A} should go first.
	 * </p>
	 * 
	 * @param board The {@link GameBoard} for <code>this</code> game.
	 * @throws NullPointerException If <code>board</code> is
	 * 			<code>null</code>.
	 * @see GameBoard
	 */
	public Game(final GameBoard board)
			throws
			NullPointerException
	{
		//May throw NullPointerException.
		this(board, Piece.PIECE_A);
	}
	/**
	 * Create a {@code Game} by with a specific {@link GameBoard} and the
	 * {@link Piece} which current turn it is.
	 * 
	 * <p>
	 * This constructor can be used to load games which are partially completed
	 * or to create custom games and game modes with specially created
	 * {@link GameBoard} and non-fixed first players.
	 * </p>
	 * 
	 * @param board The {@link GameBoard} which will be used to play the
	 * 			{@link Game}.
	 * @param currentPiece The {@link Piece} for which player's turn it
	 * 			currently is.
	 * @throws NullPointerException If either <code>board</code> or
	 * 			<code>currentPiece</code> is <code>null</code>.
	 * @see GameBoard
	 * @see Piece
	 */
	public Game(
			final GameBoard board,
			final Piece currentPiece)
			throws
			NullPointerException
	{
		if(board == null)
			throw new NullPointerException();
		if(currentPiece == null)
			throw new NullPointerException();
		this.board = board;
		current = currentPiece;
	}
	//=========================================================================
	//Methods.
	/**
	 * Go to the next turn.
	 * 
	 * <p>
	 * If a {@link Piece} does not have any legal moves, then the turn will
	 * go back to the current {@link Piece} object's turn.
	 * </p>
	 * 
	 * <p>
	 * <b>For internal use only!</b>
	 * </p>
	 */
	private void nextTurn()
	{
		if(!board.legalMoves(current.flip()).isEmpty())
			current = current.flip();
	}
	/**
	 * Check if the game is over.
	 * 
	 * @return <code>true</code> if the game has ended, otherwise, returns
	 * 			<code>false</code>.
	 */
	public final boolean isGameOver()
	{
		return board.isEnd();
	}
	/**
	 * Put the current {@link Piece} at a specific {@link Position}.
	 * 
	 * <p>
	 * After a move has been completed, the {@code Game} will move the the
	 * next turn.
	 * </p>
	 * 
	 * @param position The {@link Position} to place the {@link Piece} of the
	 * 			current player's {@link Piece}.
	 * @throws InvalidMoveException If the current {@link Piece} cannot be
	 * 			placed at <code>position</code>.
	 * @throws NullPointerException If <code>position</code> is
	 * 			<code>null</code>.
	 * @see #getCurrent()
	 * @see Position
	 */
	public final void put(final Position position)
			throws
			InvalidMoveException,
			NullPointerException
	{
		board.put(position, getCurrent());
		nextTurn();
	}
	/**
	 * Get the {@link Piece} representing the first player.
	 * 
	 * <p>
	 * The first player will always have {@link Piece#PIECE_A} as their
	 * {@link Piece}.
	 * </p>
	 * 
	 * @return The {@link Piece} of the first player.
	 */
	public final Piece getPlayer1()
	{
		return Piece.PIECE_A;
	}
	/**
	 * Get the {@link Piece} representing the second player.
	 * 
	 * <p>
	 * The second player will always have {@link Piece#PIECE_B} as their
	 * {@link Piece}.
	 * </p>
	 * 
	 * @return The {@link Piece} of the second player.
	 */
	public final Piece getPlayer2()
	{
		return Piece.PIECE_B;
	}
	/**
	 * Get the {@link Piece} which current turn it is.
	 * 
	 * @return The {@link Piece} of the player whom's turn it currently is.
	 */
	public final Piece getCurrent()
	{
		return current;
	}
	/**
	 * Get the {@link BoardView} of the board which is contained in
	 * <code>this</code> {@code Game}.
	 * 
	 * @return A {@link BoardView} object of the {@link GameBoard} for
	 * 			<code>this</code> game.
	 */
	public final BoardView getBoard()
	{
		return board.getView();
	} //getBoard()
	//=========================================================================
	//Overidden methods.
	/**
	 * 
	 */
	@Override
	public final boolean equals(Object o)
	{
		return false;
	} //equals(Object)
	/**
	 * 
	 */
	@Override
	public final int hashCode()
	{
		return 0;
	} //hashCode()
	/**
	 * 
	 */
	@Override
	public final String toString()
	{
		return "";
	} //toString()
} //Game