rm -fv bin/*.apk

javac -d tool tool/LogoGen.java 
java -cp tool LogoGen nttr

aapt package -v -f -m -S res -J src -M AndroidManifest.xml -I /usr/java/android/platforms/android-33/android.jar

javac -source 1.8 -target 1.8 -bootclasspath "/usr/java/android/platforms/android-33/android.jar": -sourcepath "src" -cp "/usr/java/android/platforms/android-33/android.jar":"obj" -g:none -proc:none -nowarn -O -Xlint:all -d "obj" "src/net/murat/nodict/NoDict.java"

/usr/java/android/build-tools/29.0.0/dx --dex --verbose --output="bin/classes.dex" "obj"

aapt package -v -f -M "AndroidManifest.xml" -A "assets" -S "res" -I "/usr/java/android/platforms/android-33/android.jar" -F "bin/NoDict.unsigned.apk" "bin"

jarsigner -keystore NoDict.keystore -storepass yourpassw -keypass yourpassw -signedjar "bin/NoDict.signed.apk" "bin/NoDict.unsigned.apk" NoDict.keystore

zipalign -v -f 4 "bin/NoDict.signed.apk" "bin/NoDict.apk"

rm -fv *.keystore.old
rm -fv bin/classes.dex
rm -fv bin/*.signed.*
rm -fv bin/*.unsigned.*
rm -fv src/net/murat/nodict/R.java
rm -rfv obj/net

ls bin
