import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_MED } from "./common";

/**
 * Left arm - angled downward slightly (cute horror doll).
 */
export default function ArmLeft({ shown = false, faint = false, className }: PartProps) {
  const shoulderX = 260;
  const shoulderY = 250;
  const handX = 220;
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
