import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_THIN } from "./common";

/**
 * Rope hanging down from the beam to the doll's head.
 */
export default function Rope({ shown = false, faint = false, className }: PartProps) {
  // Approx hang point
  const x = 300;
  const top = 60;
  const bottom = 135;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      <path
        d={`M ${x} ${top} L ${x} ${bottom}`}
        {...strokePurple(STROKE_THIN)}
      />
    </g>
  );
}