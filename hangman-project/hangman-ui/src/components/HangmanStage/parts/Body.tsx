import React from "react";
import { type PartProps, partStyle } from "./common";

/**
 * Cute-horror torso — a rounded rectangle.
 */
export default function Body({ shown = false, faint = false, className }: PartProps) {
  const x = 260;
  const y = 215;
  const width = 80;
  const height = 85;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      <rect
        x={x}
        y={y}
        width={width}
        height={height}
        rx={16}
        fill="url(#bodyFill)"
        style={{
          filter: "drop-shadow(0 0 14px rgba(148,0,255,0.4))",
        }}
      />
    </g>
  );
}