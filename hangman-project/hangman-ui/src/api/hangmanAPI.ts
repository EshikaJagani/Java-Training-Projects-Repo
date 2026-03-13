// axios calls to Spring Boot backend

// Central API layer for calling Spring Boot backend.
// Uses env var VITE_API_BASE to build absolute URLs.
// If not set, it will keep relative paths (but we are setting it).

const BASE = import.meta.env.VITE_API_BASE?.toString().trim();

export function url(path: string) {
  if (BASE) return `${BASE}${path}`;
  return path; // fallback: relative path
}

export async function startGame(difficulty: string): Promise<string> {
  const res = await fetch(
    url(`/api/hangman/start?difficulty=${encodeURIComponent(difficulty)}`),
    { method: "POST" }
  );
  if (!res.ok) throw new Error(`Failed to start game (HTTP ${res.status})`);
  return res.text();
}

export async function deleteGame(gameId: string) {
  const res = await fetch(`/api/hangman/${encodeURIComponent(gameId)}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    throw new Error(`Failed to delete game (HTTP ${res.status})`);
  }
}

export async function fetchState(gameId: string) {
  const res = await fetch(url(`/api/hangman/${encodeURIComponent(gameId)}/state`));
  if (!res.ok) throw new Error(`Failed to fetch state (HTTP ${res.status})`);
  return res.json();
}

export async function sendGuess(
  gameId: string,
  letter: string,
  opts?: { playerId?: string; playerName?: string; startTime?: number }
) {
  const params = new URLSearchParams();
  if (opts?.playerId)   params.set("playerId", opts.playerId);
  if (opts?.playerName) params.set("playerName", opts.playerName);
  if (opts?.startTime)  params.set("startTime", String(opts.startTime));

  
  const urlFinal =
    url(`/api/hangman/${encodeURIComponent(gameId)}/guess`) +
    (params.toString() ? `?${params.toString()}` : "");

  const res = await fetch(
    urlFinal,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ letter })
    }
  );
  if (!res.ok) throw new Error(`Failed to guess letter (HTTP ${res.status})`);
  return res.json();
}

export async function registerPlayer(name: string) {
  const res = await fetch(
    url(`/api/players/register?name=${encodeURIComponent(name)}`),
    { method: "POST" }
  );

  if (!res.ok) throw new Error(`Failed to register player (HTTP ${res.status})`);
  return res.json(); // returns { id, name, createdAt }
}

/*export async function sendGuess(gameId: string, letter: string) {
  const res = await fetch(
    url(`/api/hangman/${encodeURIComponent(gameId)}/guess`),
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ letter }),
    }
  );
  if (!res.ok) throw new Error(`Failed to guess letter (HTTP ${res.status})`);
  return res.json();
}*/

/*
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

//   const BASE = import.meta.env.VITE_API_BASE?.toString().trim();
// function url(path: string) {
//   if (BASE) return `${BASE}${path}`;
//   return path; // stays relative → uses proxy
// }

  return res.json();
}
*/