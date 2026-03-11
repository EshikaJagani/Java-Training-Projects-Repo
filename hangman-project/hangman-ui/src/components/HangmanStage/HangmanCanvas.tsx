// for drawing hangman on canvas

import React, { useMemo } from "react";

import Base from "./parts/Base";
import Pole from "./parts/Pole";
import Rope from "./parts/Rope";
import Head from "./parts/Head";
import Body from "./parts/Body";
import ArmLeft from "./parts/ArmLeft";
import ArmRight from "./parts/ArmRight";
import LegLeft from "./parts/LegLeft";
import LegRight from "./parts/LegRight";
import { SvgDefs } from "./parts/common";

export type Difficulty = "EASY" | "MEDIUM" | "HARD";

/**
 * Your exact progression (by step) for each difficulty.
 */
const EASY_STEPS = [
  ["Base", "Pole"],
  ["Rope"],
  ["Head"],
  ["Body"],
  ["ArmLeft"],
  ["ArmRight"],
  ["LegLeft"],
  ["LegRight"],
];

const MEDIUM_STEPS = [
  ["Base", "Pole", "Rope"],
  ["Head"],
  ["Body"],
  ["ArmLeft"],
  ["ArmRight"],
  ["LegLeft", "LegRight"],
];

const HARD_STEPS = [
  ["Base", "Pole", "Rope"],
  ["Head", "Body"],
  ["ArmLeft", "ArmRight"],
  ["LegLeft", "LegRight"],
];

const STEPS: Record<Difficulty, string[][]> = {
  EASY: EASY_STEPS,
  MEDIUM: MEDIUM_STEPS,
  HARD: HARD_STEPS,
};

type Props = {
  difficulty: Difficulty;
  mistakes: number;
  maxMistakes: number;
};

function useVisibleParts(difficulty: Difficulty, mistakes: number) {
  return useMemo(() => {
    const seq = STEPS[difficulty] || EASY_STEPS;
    const parts = new Set<string>();

    for (let i = 0; i < Math.min(mistakes, seq.length); i++) {
      for (const p of seq[i]) parts.add(p);
    }
    return parts;
  }, [difficulty, mistakes]);
}

const frame: React.CSSProperties = {
  position: "relative",
  width: "100%",
  minHeight: 360,
  borderRadius: 12,
  border: "1px dashed rgba(148,0,255,0.35)",
  boxShadow: "inset 0 0 28px rgba(148,0,255,0.14)",
  overflow: "hidden",
  background:
    "radial-gradient(60% 80% at 50% 35%, rgba(148,0,255,0.08), rgba(10,0,20,0.8))",
};

export default function HangmanCanvas({ difficulty, mistakes, maxMistakes }: Props) {
  const visibleParts = useVisibleParts(difficulty, mistakes);

  return (
    <div style={frame} aria-label="Hangman ritual chamber">
      <svg
        width="100%"
        height="100%"
        viewBox="0 0 420 420"
        style={{ position: "absolute", inset: 0 }}
      >
        <SvgDefs />

        {/* ✨ Blurred silhouette behind everything */}
        <g opacity={0.16} style={{ filter: "blur(3px)" }}>
          <Base faint />
          <Pole faint />
          <Rope faint />
          <Head faint />
          <Body faint />
          <ArmLeft faint />
          <ArmRight faint />
          <LegLeft faint />
          <LegRight faint />
        </g>

        {/* 💜 Revealed parts */}
        <Base shown={visibleParts.has("Base")} />
        <Pole shown={visibleParts.has("Pole")} />
        <Rope shown={visibleParts.has("Rope")} />
        <Head shown={visibleParts.has("Head")} />
        <Body shown={visibleParts.has("Body")} />
        <ArmLeft shown={visibleParts.has("ArmLeft")} />
        <ArmRight shown={visibleParts.has("ArmRight")} />
        <LegLeft shown={visibleParts.has("LegLeft")} />
        <LegRight shown={visibleParts.has("LegRight")} />
      </svg>
    </div>
  );
}