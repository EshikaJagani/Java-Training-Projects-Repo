import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import NeonButton from "../components/NeonButton";
import DifficultySelector, { type Difficulty } from "../components/DifficultySelector";

const container: React.CSSProperties = {
  minHeight: "100dvh",
  display: "grid",
  gridTemplateColumns: "1.1fr 0.9fr",
  alignItems: "center",
  gap: "2rem",
  padding: "3rem 2rem",
};

const leftCol: React.CSSProperties = {
  padding: "1rem",
};

const rightCol: React.CSSProperties = {
  position: "relative",
  padding: "1rem",
  display: "grid",
  placeItems: "center",
};

const title: React.CSSProperties = {
  fontFamily: "var(--font-creepy)",
  fontSize: "clamp(2.4rem, 6vw, 5rem)",
  margin: 0,
  lineHeight: 1,
  color: "var(--purple)",
  textShadow: "var(--glow-purple)",
};

const subtitle: React.CSSProperties = {
  marginTop: "0.5rem",
  fontSize: "1rem",
  color: "var(--neon-green)",
  textShadow: "var(--glow-green)"
};

const panel: React.CSSProperties = {
  marginTop: "2rem",
  display: "grid",
  gap: "1rem",
  maxWidth: 460,
  background: "linear-gradient(180deg, rgba(5,0,17,0.75), rgba(0,0,0,0.5))",
  border: "1px solid rgba(148,0,255,0.4)",
  borderRadius: 16,
  padding: "1rem 1.2rem",
  boxShadow: "0 0 18px rgba(148,0,255,0.2)",
  backdropFilter: "blur(3px)"
};

const dollWrap: React.CSSProperties = {
  position: "relative",
  width: "min(440px, 90%)",
  height: "min(420px, 68vh)",
  borderRadius: 14,
  border: "1px dashed rgba(124,255,178,0.25)",
  boxShadow: "inset 0 0 24px rgba(124,255,178,0.08)",
  overflow: "hidden",
};

const rope: React.CSSProperties = {
  position: "absolute",
  top: 0,
  left: "50%",
  transform: "translateX(-50%)",
  width: 6,
  height: "28%",
  background: "linear-gradient(180deg, #2b1a40, #0f0520)",
  boxShadow: "0 0 12px rgba(148,0,255,0.45)"
};

// This is just a decorative “preview” chibi doll silhouette for the Home screen.
// The real game drawing will be in HangmanCanvas with per-part reveal.
function ChibiDollPreview() {
  return (
    <div style={dollWrap} aria-hidden>
      <div style={rope} />

      {/* Head */}
      <div
        style={{
          position: "absolute",
          top: "26%",
          left: "50%",
          transform: "translateX(-50%)",
          width: 120,
          height: 120,
          borderRadius: "50%",
          background:
            "radial-gradient(ellipse at 50% 38%, #1f0d33 0%, #0a0317 65%)",
          boxShadow: "0 0 20px rgba(148,0,255,0.35)",
          filter: "blur(1px)",
          opacity: 0.55,
        }}
      />
      {/* Eyes */}
      <div
        style={{
          position: "absolute",
          top: "31%",
          left: "50%",
          transform: "translateX(-50%)",
          display: "flex",
          gap: 22
        }}
      >
        <div
          style={{
            width: 18,
            height: 18,
            borderRadius: "50%",
            background: "#9400ff",
            boxShadow: "0 0 14px #9400ff, 0 0 22px #9400ff",
            opacity: 0.75,
            filter: "blur(0.5px)"
          }}
        />
        <div
          style={{
            width: 18,
            height: 18,
            borderRadius: "50%",
            background: "#9400ff",
            boxShadow: "0 0 14px #9400ff, 0 0 22px #9400ff",
            opacity: 0.75,
            filter: "blur(0.5px)"
          }}
        />
      </div>

      {/* Body */}
      <div
        style={{
          position: "absolute",
          top: "49%",
          left: "50%",
          transform: "translateX(-50%)",
          width: 90,
          height: 90,
          borderRadius: 16,
          background:
            "linear-gradient(180deg, rgba(148,0,255,0.8), rgba(20,0,38,0.9))",
          boxShadow: "0 0 18px rgba(148,0,255,0.4)",
          opacity: 0.45,
          filter: "blur(1px)"
        }}
      />

      {/* Arms */}
      <div
        style={{
          position: "absolute",
          top: "52%",
          left: "50%",
          transform: "translateX(-50%)",
          width: 160,
          height: 14,
          borderRadius: 7,
          background: "linear-gradient(90deg, #150427, #2c0c4b, #150427)",
          boxShadow: "0 0 18px rgba(148,0,255,0.3)",
          opacity: 0.45,
          filter: "blur(0.8px)"
        }}
      />

      {/* Legs */}
      <div
        style={{
          position: "absolute",
          top: "68%",
          left: "50%",
          transform: "translateX(-50%)",
          width: 110,
          height: 16,
          borderRadius: 8,
          background: "linear-gradient(90deg, #0f0620, #2c0c4b, #0f0620)",
          boxShadow: "0 0 18px rgba(148,0,255,0.3)",
          opacity: 0.45,
          filter: "blur(0.8px)"
        }}
      />
    </div>
  );
}

export default function Home() {
  const [difficulty, setDifficulty] = useState<Difficulty>("EASY");
  const navigate = useNavigate();

  return (
    <main style={container}>
      {/* LEFT: Title & Controls */}
      <section style={leftCol}>
        <h1 style={title}>THE HANGMAN</h1>
        <p style={subtitle}>
          Welcome to the horror ritual of word guessing. Choose your fate and begin.
        </p>

        <div style={panel}>
          <DifficultySelector
            value={difficulty}
            onChange={setDifficulty}
            label="Choose difficulty"
          />

          <NeonButton
            variant="purple"
            fullWidth
            onClick={() => navigate(`/game?difficulty=${difficulty}`)}
            ariaLabel="Begin Ritual"
          >
            BEGIN RITUAL
          </NeonButton>
        </div>
      </section>

      {/* RIGHT: Decorative chibi silhouette preview */}
      <section style={rightCol}>
        <ChibiDollPreview />
      </section>
    </main>
  );
}