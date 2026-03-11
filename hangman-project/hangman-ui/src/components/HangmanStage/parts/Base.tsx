import React from "react";
import { CANVAS_W, CANVAS_H, type PartProps, partStyle } from "./common";

/**
 * Draws the ground plate near the bottom. Wide, with subtle glow.
 * Place inside an <svg viewBox={`0 0 ${CANVAS_W} ${CANVAS_H}`}>.
 */
export default function Base({ shown = false, faint = false, className }: PartProps) {
  const width = CANVAS_W * 0.7;
  const height = 18;
  const x = (CANVAS_W - width) / 2;
  const y = CANVAS_H - 40;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      <rect
        x={x}
        y={y}
        width={width}
        height={height}
        rx={10}
        fill="url(#groundFill)"
        style={{ filter: "drop-shadow(0 0 12px rgba(148,0,255,0.35))" }}
      />
    </g>
  );
}