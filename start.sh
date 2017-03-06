#!/bin/bash

APPID=ti.waterwaveprogress
VERSION=2.0.2

#cp android/assets/* iphone/
cd android;ant clean; rm -rf build/*;ant -v;  unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/; unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Documents/APPC_WORKSPACE/HafenRadler;cd ..

#cd iphone/; python build.py;  unzip -uo  $APPID-iphone-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..
#cp iphone/$APPID-iphone-$VERSION.zip .
