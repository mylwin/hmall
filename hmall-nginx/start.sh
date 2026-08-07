#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
# 先停止已有 nginx（带 -p 指定同一个 prefix）
nginx -p "$DIR" -s quit 2>/dev/null
# 启动
nginx -p "$DIR" -c "$DIR/conf/nginx.conf"
echo "nginx started."
echo "Portal:     http://localhost:18080"
echo "Admin:      http://localhost:18081"
echo "New Admin:  http://localhost:18082"
