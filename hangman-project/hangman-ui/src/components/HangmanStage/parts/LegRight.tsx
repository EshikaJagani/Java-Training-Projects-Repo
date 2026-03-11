import React from "react";
import { type PartProps, partStyle, strokePurple, STROKE_MED } from "./common";

/**
 * Right leg.
 */
export default function LegRight({ shown = false, faint = false, className }: PartProps) {
  const hipX = 320;
  const hipY = 300;
  const footX = 345;
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