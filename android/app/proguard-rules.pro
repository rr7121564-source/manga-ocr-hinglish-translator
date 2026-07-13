# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# signAndroidApp.enableR8=true property in gradle.properties.

-keep class com.google.mlkit.** { *; }
-keep class org.apache.commons.** { *; }
-dontwarn com.google.mlkit.**
