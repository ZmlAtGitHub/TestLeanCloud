# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# specify compress level
-optimizationpasses 5
# specify upper/lower letters
-dontusemixedcaseclassnames
# specify library classes
-dontskipnonpubliclibraryclasses
# specify preverify
-dontpreverify
# specify log
-verbose
# specify algorithm
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# ignore warnings
-ignorewarnings
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-dontoptimize
-keepattributes SourceFile,LineNumberTable

# Android
-keepnames class * extends android.view.View
-assumenosideeffects class android.util.Log {
    public static int v(...);
}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.app.Dialog
-keep public class com.android.vending.licensing.ILicensingService
-dontwarn android.support.**
-dontwarn android.support.v4.**
-dontwarn **CompatHoneycomb
-dontwarn **CompatHoneycombMR2
-dontwarn **CompatCreatorHoneycombMR2
-keep interface android.support.v4.app.** {*;}
-keep class android.support.** {*;}
-keep class android.support.v4.** {*;}
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}


# Androidx
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontwarn androidx.**
-keepnames class * extends androidx.fragment.app.Fragment
-keep class androidx.viewpager.widget.ViewPager{
    *;
}
-keep class androidx.viewpager.widget.ViewPager$**{
	*;
}

# keep R minifyDisabled,otherwise can not access to id/resource by reflecting
-keep class **.R*{*;}
# keep native

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context);
}
# keep custom view
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
# keep custom view
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# keep custom view
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
# keep enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# keep Serializable and Parcelable
-keepnames class * implements java.io.Serializable
-keep class * implements android.os.Parcelable {
    public static finalandroid.os.ParcelableCreator *;
}
# keep variables matched rules in class * implements Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <methods>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# keep JSONObject
-keepclassmembers class * {
    public <init> (org.json.JSONObject);
}
-keep class org.json.**{*;}

-keepattributes Exceptions,InnerClasses,...
-keep class cn.leancloud.A{
    *;
}
-keep class cn.leancloud.A$* {
    *;
}
-keep class cn.leancloud.**{*;}