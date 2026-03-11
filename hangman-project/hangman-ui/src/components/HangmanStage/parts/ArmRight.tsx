import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_MED } from "./common";

/**
 * Right arm.
 */
export default function ArmRight({ shown = false, faint = false, className }: PartProps) {
  const shoulderX = 340;
  const shoulderY = 250;
  const handX = 380;
  const handY = 290;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      <path
        d={`M ${shoulderX} ${shoulderY} L ${handX} ${handY}`}
        {...strokePurple(STROKE_MED)}
      />
    </g>
  );
}