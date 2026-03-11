// for game over

import React from "react";
import NeonButton from "./NeonButton";

type Props = {
  difficulty: string;
  onRetry: () => void;
  onHome: () => void;
};

export default function SkullLoseScreen({ difficulty, onRetry, onHome }: Props) {
  return (
    <div className="lose-overlay" role="dialog" aria-modal="true" aria-label="Defeat overlay">
      <section className="lose-card aura" aria-live="polite">
        {/* Skull & title */}
        <div className="flicker-purple float" style={{ marginBottom: 6 }}>
          {/* Simple skull shape using emoji for now; you can replace with an SVG later */}
          <div
            style={{
              fontSize: "clamp(3rem, 10vw, 4.2rem)",
              lineHeight: 1,
              color: "var(--purple)",
              textShadow: "var(--glow-purple)",
              fontFamily: "var(--font-creepy)",
            }}
          >
            ☠
          </div>
        </div>

        <h2 className="lose-title">YOU LOSE</h2>
        <p className="lose-sub">
          The ritual consumed the doll. <br />
          <span style={{ opacity: 0.85 }}>
            Difficulty: <b style={{ color: "var(--purple)" }}>{difficulty}</b>
          </span>
        </p>

        <div className="lose-actions">
          <NeonButton variant="green" onClick={onRetry} ariaLabel="Try again">
            TRY AGAIN
          </NeonButton>
          <NeonButton variant="purple" onClick={onHome} ariaLabel="Go to home">
            HOME
          </NeonButton>
        </div>
      </section>
    </div>
  );
}