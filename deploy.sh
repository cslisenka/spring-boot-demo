#!/bin/bash

pkill -f quoteService

dir=$(pwd)
echo $dir
rm -rf $dir/deploy/*

mkdir $dir/deploy/quote-service
cp $dir/quote-service/target/*.jar $dir/deploy/quote-service
cp $dir/quote-service/bin/start.sh $dir/deploy/quote-service
cp $dir/quote-service/src/main/resources/* $dir/deploy/quote-service