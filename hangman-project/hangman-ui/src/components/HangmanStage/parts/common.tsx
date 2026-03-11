import React from "react";

// Shared constants and helpers for the hangman SVG parts.

export const CANVAS_W = 420;
export const CANVAS_H = 420;

// Neon horror palette (matches your theme.css)
export const PURPLE = "#9400ff";
export const GREEN = "#7cffb2";
export const BG_PURPLE_DARK = "#16052c";
export const BG_PURPLE_DEEP = "#0a0216";

// Common stroke widths (scale in one place if needed)
export const STROKE_THICK = 8;
export const STROKE_MED = 6;
export const STROKE_THIN = 4;

export type PartProps = {
  /** Show this part as fully revealed (true) or faint/blurred (false). */
  shown?: boolean;
  /** Extra faint look for silhouette backdrop */
  faint?: boolean;
  /** Optional CSS className for custom styling */
  className?: string;
};

/**
 * Returns an object of SVG style props that fades in when 'shown',
 * otherwise leaves it faint/blurred for the silhouette illusion.
 */
export function partStyle(shown?: boolean, faint?: boolean): React.CSSProperties {
  const baseOpacity = faint ? 0.18 : 0.12;
  const baseBlur = faint ? 2.8 : 1.8;

  return {
    opacity: shown ? 1 : baseOpacity,
    filter: shown ? "none" : `blur(${baseBlur}px)`,
    transition: "opacity 240ms ease, filter 240ms ease",
  };
}

/** Common neon stroke look (purple) */
export function strokePurple(width = STROKE_MED): React.SVGProps<SVGPathElement> {
  return {
    stroke: PURPLE,
    strokeWidth: width,
    strokeLinecap: "round",
    strokeLinejoin: "round",
    fill: "none",
  };
}

/** Slightly filled purple element with glow */
export function filledPurple(glow = true): React.SVGProps<SVGRectElement> {
  return {
    fill: `url(#bodyFill)`,
    style: glow
      ? { filter: "drop-shadow(0 0 10px rgba(148,0,255,0.55))" }
      : undefined,
  };
}

/**
 * SVG defs you can include once in your <svg> to reuse gradient fills.
 * We'll mount this in HangmanCanvas later so all parts can use `url(#bodyFill)`.
 */

export function SvgDefs() {
  return (
    <defs>
      <linearGradient id="beamFill" x1="0" y1="0" x2="1" y2="0">
        <stop offset="0%" stopColor={BG_PURPLE_DEEP} />
        <stop offset="100%" stopColor={BG_PURPLE_DARK} />
      </linearGradient>

      <linearGradient id="groundFill" x1="0" y1="0" x2="0" y2="1">
        <stop offset="0%" stopColor="#100222" />
        <stop offset="100%" stopColor="#060012" />
      </linearGradient>

      <linearGradient id="bodyFill" x1="0" y1="0" x2="0" y2="1">
        <stop offset="0%" stopColor="#2b0b54" />
        <stop offset="100%" stopColor="#15032b" />
      </linearGradient>
    </defs>
  );
}
/*
export function SvgDefs() {
  return (
    <defs>
      <linearGradient id="beamFill" x1="0" y1="0" x2="1" y2="0">
        <stop offset="0%" stopColor={BG_PURPLE_DEEP} />
        <stop offset="100%" stopColor={BG_PURPLE_DARK} />
      </linearGradient>

      <linearGradient id="groundFill" x1="0" y1="0" x2="0" y2="1">
        <stop offset="0%" stopColor="#100222" />
        <stop offset="100%" stopColor="#060012" />
      </linearGradient>

      <linearGradient id="bodyFill" x1="0" y1="0" x2="0" y2="1">
        <stop offset="0%" stopColor="#2b0b54" />
        <stop offset="100%" stopColor="#15032b" />
      </linearGradient>
    </defs>
  );
}
*/