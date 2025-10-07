import React, { useState, useEffect } from 'react';
import './Game.css';

const Game = () => {
    const [gameState, setGameState] = useState('setup');
    const [boardSize, setBoardSize] = useState(5);
    const [playerColor, setPlayerColor] = useState('white');
    const [board, setBoard] = useState([]);
    const [currentPlayer, setCurrentPlayer] = useState('white');
    const [winner, setWinner] = useState(null);
    const [message, setMessage] = useState('');
    const [isAIMakingMove, setIsAIMakingMove] = useState(false);

    const initializeBoard = (size) => {
        const newBoard = [];
        for (let i = 0; i < size; i++) {
            const row = [];
            for (let j = 0; j < size; j++) {
                row.push(' ');
            }
            newBoard.push(row);
        }
        return newBoard;
    };

    const boardToString = (boardArray) => {
        return boardArray.flat().join('');
    };

    const stringToBoard = (str, size) => {
        const newBoard = [];
        for (let i = 0; i < size; i++) {
            const row = str.slice(i * size, (i + 1) * size).split('');
            newBoard.push(row);
        }
        return newBoard;
    };

    const startGame = async () => {
        const newBoard = initializeBoard(boardSize);
        setBoard(newBoard);
        setCurrentPlayer('white');
        setWinner(null);
        setMessage('');
        setGameState('playing');

        if (playerColor === 'black') {
            setIsAIMakingMove(true);
            await makeAIMove(newBoard);
            setIsAIMakingMove(false);
        }
    };

    const apiRequest = async (endpoint, data) => {
        try {
            const response = await fetch(`http://localhost:8080/api/${endpoint}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const responseData = await response.json();
            return responseData;
        } catch (error) {
            console.error(`API request error for ${endpoint}:`, error);
            setMessage(`Connection error: ${error.message}`);
            throw error;
        }
    };

    const checkGameStatus = async (currentBoard) => {
        try {
            const boardData = {
                size: boardSize,
                data: boardToString(currentBoard),
                nextPlayerColor: currentPlayer
            };

            const statusData = await apiRequest('square/gameStatus', boardData);

            if (statusData.status === 1) {
                const winnerColor = statusData.color === 'w' ? 'white' : 'black';
                setWinner(winnerColor);
                setMessage(`Game finished. ${winnerColor} wins!`);
                setGameState('finished');
            } else if (statusData.status === 2) {
                setWinner('draw');
                setMessage("It's a draw! No more moves available.");
                setGameState('finished');
            } else if (statusData.message) {
                setMessage(statusData.message);
            }

            return statusData;
        } catch (error) {
            console.error('Error checking game status:', error);
            return { status: 0 };
        }
    };

    const handlePlayerMove = async (row, col) => {
        if (gameState !== 'playing') return;
        if (board[row][col] !== ' ') return;
        if (currentPlayer !== playerColor) return;
        if (isAIMakingMove) return; 

        const newBoard = [...board];
        newBoard[row][col] = playerColor === 'white' ? 'w' : 'b';
        setBoard(newBoard);

        const status = await checkGameStatus(newBoard);

        if (status.status === 0) {
            const nextPlayer = currentPlayer === 'white' ? 'black' : 'white';
            setCurrentPlayer(nextPlayer);

            if (nextPlayer !== playerColor) {
                setIsAIMakingMove(true);
                await makeAIMove(newBoard);
                setIsAIMakingMove(false);
            }
        }
    };

    const makeAIMove = async (currentBoard) => {
        try {
            const boardData = {
                size: boardSize,
                data: boardToString(currentBoard),
                nextPlayerColor: currentPlayer === 'white' ? 'black' : 'white'
            };

            const moveData = await apiRequest('square/nextMove', boardData);

            if (moveData.x === undefined || moveData.y === undefined) {
                throw new Error('Invalid move data received from server');
            }

            const newBoard = [...currentBoard];
            newBoard[moveData.x][moveData.y] = playerColor === 'white' ? 'b' : 'w';
            setBoard(newBoard);

            const status = await checkGameStatus(newBoard);

            if (status.status === 0) {
                setCurrentPlayer(playerColor);
            }
        } catch (error) {
            console.error('Error making AI move:', error);
            setMessage('Error making AI move. Please try again.');
        }
    };

    const restartGame = () => {
        startGame();
    };

    const finishGame = () => {
        setGameState('setup');
        setWinner(null);
        setMessage('');
    };

    const renderCell = (row, col) => {
        const cellValue = board[row][col];
        let cellClass = 'cell';

        if (cellValue === 'w') {
            cellClass += ' white';
        } else if (cellValue === 'b') {
            cellClass += ' black';
        }

        if (isAIMakingMove || (gameState === 'playing' && currentPlayer !== playerColor)) {
            cellClass += ' disabled';
        }

        return (
            <div
                key={`${row}-${col}`}
                className={cellClass}
                onClick={() => handlePlayerMove(row, col)}
            />
        );
    };

    const renderBoard = () => {
        return (
            <div className="board">
                {board.map((row, rowIndex) => (
                    <div key={rowIndex} className="board-row">
                        {row.map((_, colIndex) => renderCell(rowIndex, colIndex))}
                    </div>
                ))}
            </div>
        );
    };

    const GameSetup = () => (
        <div className="game-setup">
            <h2>Game Setup</h2>
            <div className="setup-controls">
                <div className="control-group">
                    <label htmlFor="boardSize">Board Size:</label>
                    <input
                        id="boardSize"
                        type="number"
                        min="3"
                        max="20"
                        value={boardSize}
                        onChange={(e) => setBoardSize(parseInt(e.target.value) || 3)}
                    />
                </div>
                <div className="control-group">
                    <label htmlFor="playerColor">Your Color:</label>
                    <select
                        id="playerColor"
                        value={playerColor}
                        onChange={(e) => setPlayerColor(e.target.value)}
                    >
                        <option value="white">White</option>
                        <option value="black">Black</option>
                    </select>
                </div>
            </div>
            <button className="start-button" onClick={startGame}>
                Start Game
            </button>
        </div>
    );

    const GamePlaying = () => (
        <div className="game-playing">
            <div className="game-info">
                <div className="current-player">
                    Current Player: <span className={currentPlayer}>{currentPlayer}</span>
                </div>
                <div className="your-color">
                    Your Color: <span className={playerColor}>{playerColor}</span>
                </div>
                {isAIMakingMove && <div className="ai-thinking">AI is thinking...</div>}
                {message && <div className="game-message">{message}</div>}
            </div>
            {renderBoard()}
            <div className="game-controls">
                <button onClick={restartGame}>Restart Game</button>
                <button onClick={finishGame}>Finish Game</button>
            </div>
        </div>
    );

    const GameFinished = () => (
        <div className="game-finished">
            <h2>{message}</h2>
            <button onClick={restartGame}>Play Again</button>
        </div>
    );

    return (
        <div className="game-container">
            <h1>Square Game</h1>
            {gameState === 'setup' && <GameSetup />}
            {gameState === 'playing' && <GamePlaying />}
            {gameState === 'finished' && <GameFinished />}
        </div>
    );
};

export default Game;