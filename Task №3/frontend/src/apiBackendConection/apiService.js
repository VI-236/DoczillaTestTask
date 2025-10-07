const API_BASE_URL = 'http://localhost:8080/api';

class ApiService {
  constructor() {
    this.baseURL = API_BASE_URL;
  }

  async makeRequest(url, data) {
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      return await response.json();
    } catch (error) {
      console.error('API request failed:', error);
      throw error;
    }
  }

  /**
   * Эндпоинт 1: Получить следующий ход
   * @param {string} rules - тип правил игры
   * @param {BoardDto} boardDto - текущее состояние доски
   * @returns {Promise<SimpleMoveDto>}
   */
  async getNextMove(rules, boardDto) {
    const url = `${this.baseURL}/square/nextMove`;
    return this.makeRequest(url, boardDto);
  }

  /**
   * Эндпоинт 2: Получить статус игры
   * @param {string} rules - тип правил игры
   * @param {BoardDto} boardDto - текущее состояние доски
   * @returns {Promise<GameStatusDto>}
   */
  async getGameStatus(rules, boardDto) {
    const url = `${this.baseURL}/square/gameStatus`;
    return this.makeRequest(url, boardDto);
  }
}

export default new ApiService();