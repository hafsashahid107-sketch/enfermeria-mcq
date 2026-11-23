#!/bin/bash
# Wraps index.html (artifact version) into a full installable page in app/
cd "$(dirname "$0")"
{
  echo '<!doctype html><html lang="es"><head><meta charset="utf-8">'
  echo '<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">'
  echo '<meta name="theme-color" content="#0C447C">'
  echo '<link rel="manifest" href="manifest.webmanifest"><link rel="icon" href="icon-192.png"><link rel="apple-touch-icon" href="icon-192.png">'
  echo '<style>:root{padding-top:env(safe-area-inset-top,0px);padding-bottom:env(safe-area-inset-bottom,0px)}body{margin:0}[hidden]{display:none!important}</style>'
  echo '</head><body>'
  cat src/index.html
  echo '<script>if("serviceWorker" in navigator){addEventListener("load",()=>navigator.serviceWorker.register("sw.js"))}</script>'
  echo '</body></html>'
} > index.html
echo built
