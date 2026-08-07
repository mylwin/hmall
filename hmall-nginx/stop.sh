#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
nginx -p "$DIR" -s quit
echo "nginx stopped."
