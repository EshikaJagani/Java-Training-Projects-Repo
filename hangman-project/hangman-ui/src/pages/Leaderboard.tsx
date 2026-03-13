import React, { useEffect, useState } from "react";
import { url } from "../api/hangmanAPI"; // use your url() helper

export default function Leaderboard() {
  const [scores, setScores] = useState([]);
  const [difficulty, setDifficulty] = useState("EASY");

  useEffect(() => {
    fetch(url(`/api/highscores/top?difficulty=${difficulty}`))
      .then((res) => res.json())
      .then(setScores)
      .catch(console.error);
  }, [difficulty]);

  return (
    <div style={{ padding: "2rem", color: "var(--neon-green)" }}>
      <h1 style={{ textShadow: "var(--glow-purple)" }}>Leaderboard</h1>

      <select
        value={difficulty}
        onChange={(e) => setDifficulty(e.target.value)}
        style={{
          padding: "0.4rem 0.8rem",
          marginBottom: "1.5rem",
          background: "black",
          color: "var(--neon-green)",
          border: "1px solid var(--purple)"
        }}
      >
        <option value="EASY">EASY</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HARD">HARD</option>
      </select>

      <table
        style={{
          width: "100%",
          borderCollapse: "collapse",
          background: "rgba(0,0,0,0.4)"
        }}
      >
        <thead>
          <tr style={{ color: "var(--purple)", textShadow: "var(--glow-purple)" }}>
            <th>Player</th>
            <th>Mistakes</th>
            <th>Time (s)</th>
            <th>Win</th>
            <th>Date</th>
          </tr>
        </thead>

        <tbody>
          {scores.map((s: any) => (
            <tr key={s.id} style={{ textAlign: "center" }}>
              <td>{s.playerName}</td>
              <td>{s.mistakes}</td>
              <td>{s.timeTaken}</td>
              <td>{s.won ? "✔" : "❌"}</td>
              <td>{new Date(s.playedAt).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}