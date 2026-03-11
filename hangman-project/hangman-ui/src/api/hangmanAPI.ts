// axios calls to Spring Boot backend

// Central API layer for calling Spring Boot backend

export async function startGame(difficulty: string): Promise<string> {
  const res = await fetch(`/api/hangman/start?difficulty=${encodeURIComponent(
    difficulty
  )}`, {
    method: "POST",
  });

  if (!res.ok) {
    throw new Error(`Failed to start game (HTTP ${res.status})`);
  }

  return res.text();
}

export async function fetchState(gameId: string) {
  const res = await fetch(`/api/hangman/${encodeURIComponent(gameId)}/state`);

  if (!res.ok) {
    throw new Error(`Failed to fetch state (HTTP ${res.status})`);
  }

  return res.json();
}

export async function sendGuess(gameId: string, letter: string) {
  const res = await fetch(`/api/hangman/${encodeURIComponent(gameId)}/guess`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ letter }),
  });

  if (!res.ok) {
    throw new Error(`Failed to guess letter (HTTP ${res.status})`);
  }

  return res.json();
}