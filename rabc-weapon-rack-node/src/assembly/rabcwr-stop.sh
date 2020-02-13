#!/bin/bash
# 程序的根目录
basedir=/usr/share/rabcwr

PID=$(cat $basedir/rabcwr.pid)
kill "$PID"
