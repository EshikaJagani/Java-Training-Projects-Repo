import React, { useEffect, useMemo, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import Keyboard from "../components/Keyboard";
import type { GameStateResponse } from "../types/GameState";
import NeonButton from "../components/NeonButton";
import HangmanCanvas from "../components/HangmanStage/HangmanCanvas";
import SkullLoseScreen from "../components/SkullLoseScreen";

// import { startGame as apiStart, fetchState as apiState, sendGuess as apiGuess } from "../api/hangmanAPI";
import {
  startGame as apiStart,
  fetchState as apiState,
  sendGuess as apiGuess,
  deleteGame as apiDelete,
} from "../api/hangmanAPI";


// Reuse Difficulty type from the selector to keep it consistent
type Difficulty = "EASY" | "MEDIUM" | "HARD";

const wrap: React.CSSProperties = {
  minHeight: "100dvh",
  display: "grid",
  gridTemplateRows: "auto 1fr auto",
  gap: "1.25rem",
  padding: "1.25rem 1.25rem 2rem",
};

const topBar: React.CSSProperties = {
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  gap: "1rem",
};

const statPill: React.CSSProperties = {
  padding: "0.5rem 0.8rem",
  border: "1px solid rgba(124,255,178,0.45)",
  borderRadius: 999,
  color: "var(--neon-green)",
  textShadow: "var(--glow-green)",
  fontFamily: "var(--font-clean)",
  background: "linear-gradient(180deg, rgba(8,8,8,0.8), rgba(0,0,0,0.5))",
};

const stageArea: React.CSSProperties = {
  display: "grid",
  gridTemplateColumns: "1.1fr 0.9fr",
  gap: "1.25rem",
  width: "100%",
  maxWidth: 1080,
  margin: "0 auto",
};

const canvasPlaceholder: React.CSSProperties = {
  minHeight: 360,
  borderRadius: 12,
  border: "1px dashed rgba(148,0,255,0.35)",
  display: "grid",
  placeItems: "center",
  boxShadow: "inset 0 0 28px rgba(148,0,255,0.12)",
};

const wordBox: React.CSSProperties = {
  display: "grid",
  gap: "1rem",
  borderRadius: 12,
  border: "1px solid rgba(124,255,178,0.25)",
  background: "linear-gradient(180deg, rgba(5,0,17,0.55), rgba(0,0,0,0.4))",
  padding: "1rem",
  boxShadow: "0 0 18px rgba(124,255,178,0.12)",
};

const wordDisplay: React.CSSProperties = {
  fontSize: "1.8rem",
  letterSpacing: 3,
  textAlign: "center" as const,
  color: "var(--neon-green)",
  textShadow: "var(--glow-green)",
  fontFamily: "var(--font-clean)",
  marginTop: 6,
};

const bottomArea: React.CSSProperties = {
  width: "100%",
  maxWidth: 1080,
  margin: "0 auto",
  display: "grid",
  gap: "0.9rem",
};

export default function Game() {
  const [params] = useSearchParams();
  const navigate = useNavigate();

  const difficulty: Difficulty = useMemo(() => {
    const d = (params.get("difficulty") || "EASY").toUpperCase();
    return ["EASY", "MEDIUM", "HARD"].includes(d) ? (d as Difficulty) : "EASY";
  }, [params]);

  const [gameId, setGameId] = useState<string>("");
  const [state, setState] = useState<GameStateResponse | null>(null);
  const [guessed, setGuessed] = useState<Set<string>>(new Set());
  const [loading, setLoading] = useState<boolean>(false);
  const [startTime, setStartTime] = useState<number>(Date.now());

  const playerId = localStorage.getItem("playerId") || "anonymous";
  const playerName = localStorage.getItem("playerName") || "Anonymous";

  // --- Backend calls (inline for now; we’ll move them to src/api soon) ---
  async function startGame(d: Difficulty) {
  return apiStart(d);
}

async function fetchState(id: string) {
  return apiState(id);
}

/*async function postGuess(id: string, letter: string) {
  return apiGuess(id, letter);
}*/
async function postGuess(
  id: string,
  letter: string,
  opts?: { playerId?: string; playerName?: string; startTime?: number }
) {
  return apiGuess(id, letter, opts);
}
  /*async function startGame(d: Difficulty) {
    const res = await fetch(`/api/hangman/start?difficulty=${encodeURIComponent(d)}`, {
      method: "POST",
    });
    if (!res.ok) throw new Error(`Start failed: ${res.status}`);
    const id = await res.text();
    return id;
  }

  async function fetchState(id: string) {
    const res = await fetch(`/api/hangman/${encodeURIComponent(id)}/state`);
    if (!res.ok) throw new Error(`State failed: ${res.status}`);
    const data = (await res.json()) as GameStateResponse;
    return data;
  }

  async function postGuess(id: string, letter: string) {
    const res = await fetch(`/api/hangman/${encodeURIComponent(id)}/guess`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ letter }),
    });
    if (!res.ok) throw new Error(`Guess failed: ${res.status}`);
    const data = (await res.json()) as GameStateResponse;
    return data;
  }*/
  // ----------------------------------------------------------------------

  // Start a new game on mount or if difficulty changes (user navigated back)
  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const id = await startGame(difficulty);
        setGameId(id);
        setStartTime(Date.now());
        const s = await fetchState(id);
        setState(s);
        setGuessed(new Set());
      } catch (e: any) {
        console.error(e);
        alert("Failed to start the game. Is the backend running on :8080?");
        navigate("/");
      } finally {
        setLoading(false);
      }
    })();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [difficulty]);

  // Keyboard guess handler
  async function handleGuess(letter: string) {
    if (!gameId || !state || state.gameOver) return;
    if (guessed.has(letter.toUpperCase())) return;

    try {
      setLoading(true);
      // const next = await postGuess(gameId, letter);
      const playerId = localStorage.getItem("playerId") || "anonymous";
      const playerName = localStorage.getItem("playerName") || "Anonymous";
      // const next = await postGuess(gameId, letter, {
      const next = await apiGuess(gameId, letter, {
        playerId,
        playerName,
        startTime
      });
      setState(next);
      setGuessed((prev) => new Set(prev).add(letter.toUpperCase()));
    } catch (e) {
      console.error(e);
      alert("Guess failed. Check backend logs.");
    } finally {
      setLoading(false);
    }
  }

  // Restart (same difficulty)
  /*
  async function handleRestart() {
  try {
    setLoading(true);

    // 1) tiny delete operation
    if (gameId) {
      await apiDelete(gameId);
    }

    // 2) create a brand‑new game
    const id = await startGame(difficulty);
    setGameId(id);

    const s = await fetchState(id);
    setState(s);

    setGuessed(new Set());
  } catch (e) {
    console.error(e);
    alert("Failed to restart.");
  } finally {
    setLoading(false);
  }
}*/
  async function handleRestart() {
    try {
      setLoading(true);
      const id = await startGame(difficulty);
      setGameId(id);
      setStartTime(Date.now());
      const s = await fetchState(id);
      setState(s);
      setGuessed(new Set());
    } catch (e) {
      console.error(e);
      alert("Failed to restart.");
    } finally {
      setLoading(false);
    }
  }
  async function handleExit() {
  try {
    if (gameId) {
      await apiDelete(gameId); // tiny delete operation
    }
  } catch (e) {
    console.warn("Delete failed on exit, but continuing anyway.", e);
  } finally {
    navigate("/");
  }
}

  const mistakesInfo =
    state ? `${state.mistakes}/${state.maxMistakes}` : "0/?";

  return (
    <main style={wrap}>
      {/* Top bar */}
      <div style={topBar}>
        <div style={{ display: "flex", alignItems: "center", gap: 10 }}>
          <span style={{ ...statPill, borderColor: "rgba(148,0,255,0.45)" }}>
            Difficulty: <b style={{ marginLeft: 6, color: "var(--purple)" }}>{difficulty}</b>
          </span>
          <span style={statPill}>
            Player:{" "}
            <b style={{ marginLeft: 6, color: "var(--neon-green)" }}>
              {localStorage.getItem("playerName") || "Anonymous"}
            </b>
          </span>
          <span style={statPill}>Mistakes: <b style={{ marginLeft: 6 }}>{mistakesInfo}</b></span>
        </div>

        <div style={{ display: "flex", gap: 10 }}>
          <NeonButton variant="green" onClick={handleRestart} ariaLabel="Restart game">
            Restart
          </NeonButton>
          {/* <NeonButton variant="purple" onClick={() => navigate("/")} ariaLabel="Back to home">
            Exit
          </NeonButton> */}
          <NeonButton variant="purple" onClick={handleExit} ariaLabel="Back to home">
            Exit
          </NeonButton>
        </div>
      </div>

      {/* Middle: Stage + Word box */}
      <section style={stageArea}>
        {/* LEFT: HangmanCanvas placeholder (we’ll build the real one next batch) */}
        {/* <div style={canvasPlaceholder}>
          <div style={{ textAlign: "center" }}>
            <h2 style={{ margin: 0 }}>Ritual Chamber</h2>
            <p style={{ marginTop: 8, opacity: 0.8 }}>
              (Hangman drawing will appear here — with blurred silhouette & per‑difficulty reveal)
            </p>
          </div>
        </div> */}
        <div style={{ position: "relative" }}>
          <HangmanCanvas
            difficulty={difficulty}
            mistakes={state?.mistakes ?? 0}
            maxMistakes={state?.maxMistakes ?? 8}
          />

          {/* If game over on loss, show a subtle overlay to go to Game Over page */}
          {state?.gameOver && !state?.win && (
          <SkullLoseScreen
          difficulty={difficulty}
          mistakes = {state?.mistakes ?? 0}
          startTime={startTime}
          onRetry={handleRestart}
          onHome={() => navigate("/")}
        />

    // <div
    //   style={{
    //     position: "absolute",
    //     bottom: 12,
    //     right: 12,
    //     display: "flex",
    //     gap: 8,
    //   }}
    // >
    //   <NeonButton
    //     variant="purple"
    //     onClick={() =>
    //       // pass current query so GameOver can restart with same difficulty
    //       navigate("/game-over", {
    //         state: { queryString: window.location.search.replace(/^\?/, "") },
    //       })
    //     }
    //   >
    //     Continue
    //   </NeonButton>
    // </div>
  )}
</div>

        {/* RIGHT: Word + Status */}
        <div style={wordBox}>
          <h3 style={{ margin: 0, color: "var(--purple)", textShadow: "var(--glow-purple)" }}>
            Word
          </h3>

          <div style={wordDisplay}>
            {state?.progress || ""}
          </div>

          {state?.gameOver && (
            <div
              style={{
                marginTop: 8,
                padding: "0.7rem 0.9rem",
                borderRadius: 10,
                border: `1px solid ${state.win ? "var(--neon-green)" : "var(--purple)"}`,
                color: state.win ? "var(--neon-green)" : "var(--purple)",
                textShadow: state.win ? "var(--glow-green)" : "var(--glow-purple)",
                background: "linear-gradient(180deg, rgba(10,10,10,0.8), rgba(0,0,0,0.55))",
              }}
            >
              {state.win ? "The curse has lifted... for now. 💀" : "The ritual consumed the doll. ❌"}
            </div>
          )}
        </div>
      </section>

      {/* Bottom: Keyboard */}
      <section style={bottomArea}>
        <Keyboard
          onGuess={handleGuess}
          disabledLetters={guessed}
          disabled={loading || !!state?.gameOver}
        />
      </section>
    </main>
  );
}