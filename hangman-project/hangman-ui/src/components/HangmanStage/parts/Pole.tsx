import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_THICK, CANVAS_H } from "./common";

/**
 * Draws the main pole and horizontal beam (neon stroke).
 * Hang point ~25% from the right end of the beam.
 */
export default function Pole({ shown = false, faint = false, className }: PartProps) {
  const left = 90;
  const baseY = CANVAS_H - 40;
  const topY = 60;
  const beamRight = 330;
  const beamLeft = left + 12;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      {/* vertical pole */}
      <path d={`M ${left},${baseY} L ${left},${topY}`} {...strokePurple(STROKE_THICK)} />
      {/* top beam */}
      <path d={`M ${beamLeft},${topY} L ${beamRight},${topY}`} {...strokePurple(STROKE_THICK)} />
      {/* diagonal brace */}
      <path d={`M ${left + 30},${topY + 10} L ${beamLeft},${topY}`} {...strokePurple()} />
    </g>
  );
}