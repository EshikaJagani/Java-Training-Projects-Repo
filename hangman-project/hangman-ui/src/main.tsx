import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./styles/animations.css";

// GLOBAL STYLES
import "./styles/theme.css";
import "@fontsource/creepster";
import "@fontsource/jetbrains-mono";

ReactDOM.createRoot(document.getElementById("root")!).render(
  // <React.StrictMode>
    <App />
  // </React.StrictMode>
);

// import { StrictMode } from 'react'
// import { createRoot } from 'react-dom/client'
// import './index.css'
// import App from './App.tsx'
// import "@fontsource/creepster";
// import "@fontsource/jetbrains-mono";
// import "./styles/theme.css";

// createRoot(document.getElementById('root')!).render(
//   <StrictMode>
//     <App />
//   </StrictMode>,
// )
