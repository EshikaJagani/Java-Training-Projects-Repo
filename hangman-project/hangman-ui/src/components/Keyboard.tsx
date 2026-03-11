// for A-Z keys in purple UI

import React from "react";

type KeyboardProps = {
  onGuess: (letter: string) => void;
  disabledLetters?: Set<string>;
  disabled?: boolean;
};

const grid: React.CSSProperties = {
  display: "grid",
  gridTemplateColumns: "repeat(13, minmax(0, 1fr))",
  gap: 8,
  width: "100%",
  maxWidth: 720,
};

const keyBase: React.CSSProperties = {
  borderRadius: 8,
  padding: "0.6rem 0",
  textAlign: "center",
  border: "1px solid var(--purple)",
  color: "var(--neon-green)",
  background: "linear-gradient(180deg, rgba(10,10,10,0.85), rgba(0,0,0,0.55))",
  textShadow: "var(--glow-green)",
  fontFamily: "var(--font-clean)",
  userSelect: "none",
  transition: "transform 100ms ease, box-shadow 150ms ease, opacity 150ms ease",
  boxShadow: "0 0 0 rgba(0,0,0,0)",
  cursor: "pointer",
};

const keyDisabled: React.CSSProperties = {
  opacity: 0.35,
  cursor: "not-allowed",
  filter: "grayscale(0.4)",
};

export default function Keyboard({
  onGuess,
  disabledLetters = new Set(),
  disabled = false,
}: KeyboardProps) {
  const letters = Array.from({ length: 26 }, (_, i) =>
    String.fromCharCode("A".charCodeAt(0) + i)
  );

  return (
    <div style={grid}>
      {letters.map((L) => {
        const isDisabled = disabled || disabledLetters.has(L);
        return (
          <button
            key={L}
            aria-label={`Guess ${L}`}
            style={{ ...keyBase, ...(isDisabled ? keyDisabled : {}) }}
            onMouseEnter={(e) => {
              if (isDisabled) return;
              e.currentTarget.style.boxShadow = "0 0 14px var(--purple)";
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.boxShadow = "0 0 0 rgba(0,0,0,0)";
            }}
            onMouseDown={(e) => {
              if (isDisabled) return;
              e.currentTarget.style.transform = "translateY(1px)";
            }}
            onMouseUp={(e) => {
              if (isDisabled) return;
              e.currentTarget.style.transform = "translateY(0)";
            }}
            disabled={isDisabled}
            onClick={() => {
              if (isDisabled) return;
              onGuess(L.toLowerCase());
            }}
          >
            {L}
          </button>
        );
      })}
    </div>
  );
}