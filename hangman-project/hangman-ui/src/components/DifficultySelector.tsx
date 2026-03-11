import React from "react";

export type Difficulty = "EASY" | "MEDIUM" | "HARD";

type Props = {
  value: Difficulty;
  onChange: (d: Difficulty) => void;
  label?: string;
};

const selectStyle: React.CSSProperties = {
  padding: "0.6rem 0.8rem",
  borderRadius: 8,
  border: "1px solid var(--purple)",
  background: "rgba(0,0,0,0.45)",
  color: "var(--neon-green)",
  textShadow: "var(--glow-green)",
  fontFamily: "var(--font-clean)",
  letterSpacing: 0.5
};

const labelStyle: React.CSSProperties = {
  display: "block",
  marginBottom: 8,
  color: "var(--purple)",
  textShadow: "var(--glow-purple)",
  fontFamily: "var(--font-clean)"
};

export default function DifficultySelector({ value, onChange, label }: Props) {
  return (
    <div>
      {label && <label style={labelStyle}>{label}</label>}
      <select
        aria-label="Difficulty"
        style={selectStyle}
        value={value}
        onChange={(e) => onChange(e.target.value as Difficulty)}
      >
        <option value="EASY">EASY (8 steps)</option>
        <option value="MEDIUM">MEDIUM (6 steps)</option>
        <option value="HARD">HARD (4 steps)</option>
      </select>
    </div>
  );
}