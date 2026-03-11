import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_MED } from "./common";

/**
 * Left leg.
 */
export default function LegLeft({ shown = false, faint = false, className }: PartProps) {
  const hipX = 280;
  const hipY = 300;
  const footX = 255;
  const footY = 355;

  return (
    <g className={className} style={partStyle(shown, faint)}>
      <path
        d={`M ${hipX} ${hipY} L ${footX} ${footY}`}
        {...strokePurple(STROKE_MED)}
      />
    </g>
  );
}