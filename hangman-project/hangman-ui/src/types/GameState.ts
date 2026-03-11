// TS types
export interface GameStateResponse {
  progress: string;   // e.g. "_ a _ g m a n"
  mistakes: number;
  maxMistakes: number;
  win: boolean;
  gameOver: boolean;
}