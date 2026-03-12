import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// IMPORTANT: This proxy makes /api calls go to Spring Boot automatically.
// No CORS issues. No manual headers.
export default defineConfig({
  plugins: [react()],
  // server: {
  //   proxy: {
  //     "/api": {
  //       target: "http://localhost:8080",
  //       changeOrigin: true,
  //     },
  //   },
  // },
});

// import { defineConfig } from 'vite'
// import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [react()],
//   server: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8080',
//         changeOrigin: true
//       }
//     }
//   }
// })

// import { defineConfig } from 'vite'
// import react from '@vitejs/plugin-react'

// // https://vite.dev/config/
// export default defineConfig({
//   plugins: [react()],
// })
