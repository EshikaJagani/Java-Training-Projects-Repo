import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import NeonButton from "../components/NeonButton";

const wrap: React.CSSProperties = {
  minHeight: "100dvh",
  display: "grid",
  placeItems: "center",
  padding: "2rem",
};

const panel: React.CSSProperties = {
  width: "min(780px, 92vw)",
  borderRadius: 18,
  border: "1px solid rgba(148,0,255,0.4)",
  background:
    "radial-gradient(60% 70% at 50% 30%, rgba(148,0,255,0.2), rgba(10,0,20,0.85))",
  boxShadow: "0 0 24px rgba(148,0,255,0.25), inset 0 0 24px rgba(124,255,178,0.08)",
  padding: "2rem",
  textAlign: "center",
};

const skull: React.CSSProperties = {
  fontFamily: "var(--font-creepy)",
  color: "var(--purple)",
  textShadow: "var(--glow-purple)",
  fontSize: "clamp(2.6rem, 7vw, 4rem)",
  margin: "0 0 0.25rem 0",
  letterSpacing: 2,
};

const text: React.CSSProperties = {
  color: "var(--neon-green)",
  textShadow: "var(--glow-green)",
  margin: "0.2rem 0 1.2rem 0",
};

export default function GameOver() {
  const navigate = useNavigate();
  const loc = useLocation();
  const params = new URLSearchParams((loc.state as any)?.queryString ?? "");
  const difficulty = params.get("difficulty") ?? "EASY";

  return (
    <main style={wrap}>
      <section style={panel} aria-live="polite">
        <h1 style={skull}>☠ YOU LOSE ☠</h1>
        <p style={text}>The ritual consumed the doll. The word eluded you.</p>

        <div style={{ display: "flex", justifyContent: "center", gap: 12, marginTop: 18 }}>
          <NeonButton
            variant="green"
            onClick={() => navigate(`/game?difficulty=${difficulty}`)}
            ariaLabel="Try again"
          >
            TRY AGAIN
          </NeonButton>
          <NeonButton
            variant="purple"
            onClick={() => navigate("/")}
            ariaLabel="Back to home"
          >
            HOME
          </NeonButton>
        </div>
      </section>
    </main>
  );
}