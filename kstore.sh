rm -fv *.keystore*
rm -fv bin/classes.dex
rm -fv bin/*.signed.*
rm -fv bin/*.unsigned.*
rm -fv bin/*.apk
rm -fv src/net/murat/nodict/R.java
rm -rfv obj/*
rm -rfv obj/net

keytool -genkey -alias NoDict.keystore -keyalg RSA -validity 10000 -dname "CN=Murat inan, OU=Freelance, O=Traductor, S=Sivas, C=TR" -keystore NoDict.keystore -storepass yourpassw -keypass yourpassw

keytool -importkeystore -srckeystore NoDict.keystore -destkeystore NoDict.keystore -deststoretype pkcs12
