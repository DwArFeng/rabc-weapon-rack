#!/bin/bash
# 程序的根目录
basedir=/usr/local/rabcwr

PID=$(cat $basedir/rabcwr.pid)
kill "$PID"
