// for game over

// import React from "react";

import React, { useEffect, useState } from "react";
import NeonButton from "./NeonButton";

type Props = {
  difficulty: string;
  mistakes: number;
  startTime: number;          // epoch ms captured when game started
  onRetry: () => void;
  onHome: () => void;
};

export default function SkullLoseScreen({
  difficulty,
  mistakes,
  startTime,
  onRetry,
  onHome,
}: Props) {
  // Freeze the time taken at the moment the overlay appears (don’t keep ticking)
  const [timeTakenSec, setTimeTakenSec] = useState<number>(0);

  useEffect(() => {
    const secs = Math.max(0, Math.floor((Date.now() - startTime) / 1000));
    setTimeTakenSec(secs);
  }, [startTime]);

  return (
    <div className="lose-overlay" role="dialog" aria-modal="true" aria-label="Defeat overlay">
      <section className="lose-card aura" aria-live="polite">
        {/* Skull & title */}
        <div className="flicker-purple float" style={{ marginBottom: 6 }}>
          <div
            style={{
              fontSize: "clamp(3.5rem, 10vw, 5rem)",
              lineHeight: 1,
              color: "#d300ff",
              textShadow:
                "0 0 8px #d300ff, 0 0 16px #d300ff, 0 0 28px #d300ff, 0 0 40px #9400ff",
              filter: "drop-shadow(0 0 35px #b300ff)",
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

        {/* 👇 Replaced the old state/startTime references with props-driven values */}
        <p className="lose-sub" style={{ marginTop: "0.4rem" }}>
          Mistakes: <b>{mistakes}</b> <br />
          Time Taken: <b>{timeTakenSec} seconds</b>
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

/*import React, { useEffect, useState } from "react";
import NeonButton from "./NeonButton";

type Props = {
  difficulty: string;
  mistakes: number;
  startTime: number;          // epoch ms captured when game started
  onRetry: () => void;
  onHome: () => void;
};

export default function SkullLoseScreen({ difficulty, mistakes, startTime, onRetry, onHome }: Props) {
  
  // Freeze the time taken at the moment the overlay appears (don’t keep ticking)
  const [timeTakenSec, setTimeTakenSec] = useState<number>(0);

  useEffect(() => {
    const secs = Math.max(0, Math.floor((Date.now() - startTime) / 1000));
    setTimeTakenSec(secs);
  }, [startTime]);

  return (
    <div className="lose-overlay" role="dialog" aria-modal="true" aria-label="Defeat overlay">
      <section className="lose-card aura" aria-live="polite">
        // { Skull & title }
        <div className="flicker-purple float" style={{ marginBottom: 6 }}>
          // { Simple skull shape using emoji for now; you can replace with an SVG later }
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
        <p className="lose-sub" style={{ marginTop: "0.4rem" }}>
          Mistakes: <b>{state?.mistakes}</b> <br />
          Time Taken:{" "}
          <b>
            {Math.floor((Date.now() - startTime) / 1000)} seconds
          </b>
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
}*/