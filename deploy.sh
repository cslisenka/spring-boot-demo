#!/bin/bash

pkill -f quoteService
pkill -f streamingService

dir=$(pwd)
echo $dir
rm -rf $dir/deploy/*

mkdir $dir/deploy/streaming-service
cp $dir/streaming-service/target/*.jar $dir/deploy/streaming-service
cp $dir/streaming-service/bin/start.sh $dir/deploy/streaming-service
cp $dir/streaming-service/src/main/resources/* $dir/deploy/streaming-service

mkdir $dir/deploy/quote-service
cp $dir/quote-service/target/*.jar $dir/deploy/quote-service
cp $dir/quote-service/bin/start.sh $dir/deploy/quote-service
cp $dir/quote-service/src/main/resources/* $dir/deploy/quote-service