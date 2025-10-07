// Модели для соответствия Java DTO
export interface BoardDto {
    size: number;
    data: string;
    nextPlayerColor: string;
}

export interface SimpleMoveDto {
    x: number;
    y: number;
    color: string;
}

export interface GameStatusDto {
    status: number;
    color: string;
    message: string;
}

// Дополнительные типы для фронтенда
export interface GameState {
    board: BoardDto;
    currentMove: SimpleMoveDto | null;
    gameStatus: GameStatusDto | null;
    loading: boolean;
    error: string | null;
}