import React from "react";

type NeonButtonProps = {
  children: React.ReactNode;
  onClick?: () => void;
  type?: "button" | "submit" | "reset";
  variant?: "purple" | "green";
  disabled?: boolean;
  fullWidth?: boolean;
  ariaLabel?: string;
};

export default function NeonButton({
  children,
  onClick,
  type = "button",
  variant = "purple",
  disabled = false,
  fullWidth = false,
  ariaLabel
}: NeonButtonProps) {
  const isPurple = variant === "purple";
  const baseColor = isPurple ? "var(--purple)" : "var(--neon-green)";
  const baseGlow = isPurple ? "var(--glow-purple)" : "var(--glow-green)";

  const style: React.CSSProperties = {
    width: fullWidth ? "100%" : undefined,
    padding: "0.9rem 1.25rem",
    borderRadius: 10,
    border: `1px solid ${baseColor}`,
    color: baseColor,
    background:
      "linear-gradient(180deg, rgba(10,10,10,0.8), rgba(0,0,0,0.6))",
    textShadow: baseGlow,
    boxShadow: `0 0 0 rgba(0,0,0,0)`,
    letterSpacing: 1,
    fontSize: "1rem",
    fontFamily: "var(--font-clean)",
    transition: "transform 120ms ease, box-shadow 200ms ease, color 200ms ease",
    opacity: disabled ? 0.5 : 1,
    cursor: disabled ? "not-allowed" : "pointer",
    backdropFilter: "blur(2px)"
  };

  return (
    <button
      aria-label={ariaLabel}
      type={type}
      onClick={disabled ? undefined : onClick}
      style={style}
      onMouseEnter={(e) => {
        (e.currentTarget.style.boxShadow as any) = `0 0 18px ${baseColor}`;
      }}
      onMouseLeave={(e) => {
        (e.currentTarget.style.boxShadow as any) = "0 0 0 rgba(0,0,0,0)";
      }}
      onMouseDown={(e) => {
        e.currentTarget.style.transform = "translateY(1px)";
      }}
      onMouseUp={(e) => {
        e.currentTarget.style.transform = "translateY(0)";
      }}
      disabled={disabled}
    >
      {children}
    </button>
  );
}