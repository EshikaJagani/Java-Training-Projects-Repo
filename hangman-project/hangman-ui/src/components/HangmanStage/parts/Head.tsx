import React from "react";
import { type PartProps, partStyle, PURPLE } from "./common";

/**
 * Cute-horror neon head: a circle with big glowing eyes.
 */
export default function Head({ shown = false, faint = false, className }: PartProps) {
  const cx = 300;
  const cy = 175;
  const r = 42;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      {/* Head shape */}
      <circle
        cx={cx}
        cy={cy}
        r={r}
        fill="url(#bodyFill)"
        style={{
          filter: "drop-shadow(0 0 12px rgba(148,0,255,0.45))",
        }}
      />

      {/* Eyes */}
      <circle
        cx={cx - 16}
        cy={cy - 4}
        r={8}
        fill={PURPLE}
        style={{ filter: "drop-shadow(0 0 10px rgba(148,0,255,0.9))" }}
      />
      <circle
        cx={cx + 16}
        cy={cy - 4}
        r={8}
        fill={PURPLE}
        style={{ filter: "drop-shadow(0 0 10px rgba(148,0,255,0.9))" }}
      />
    </g>
  );
}