# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontobfuscate
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable

-keep class android.support.v7.widget.SearchView { *; }

#realm optimisations
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-dontwarn io.realm.**

#okhttp optimisations
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn okio.**
-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

#Retrofit optimisations
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

-keep class sun.misc.Unsafe { *; }

#Joda time
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

#jts
-dontwarn java.awt.**

#ViewPagerIndicator
-dontwarn android.util.FloatMath

#volley
-injars libs/volley.jar

#Optimizely
-dontwarn com.optimizely.**
-dontwarn org.slf4j.**

#Otto
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-keepclassmembers class * {
   public void *(android.view.View);
}
#Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keep class io.fabric.sdk.android.** { *; }
-keep interface io.fabric.sdk.android.** { *; }
-keep class io.fabric.** { *; }
-keep interface io.fabric.** { *; }

#Adjust
-keep class com.adjust.sdk.plugin.MacAddressUtil { <methods>; }

#Reflection
-keep public class com.momondo.common.adapters.FilterableAdapter
-keep public class * extends com.momondo.common.adapters.FilterableAdapter

-keepclassmembers class * extends com.momondo.common.adapters.FilterableAdapter {
    public <init>(android.content.Context);
}
-keepclassmembers class com.momondo.common.adapters.FilterableAdapter {
    public <init>(android.content.Context);
}

-keep public class com.momondo.common.utils.DataController
-keep public class * extends com.momondo.common.utils.DataController

-keepclassmembers class com.momondo.common.utils.DataController {
    public <init>(com.momondo.common.adapters.FilterableAdapter);
}

-keepclassmembers class * extends com.momondo.common.utils.DataController {
    public <init>(com.momondo.common.adapters.FilterableAdapter);
}

-keep public class com.momondo.common.** {
  public protected *;
}

#Appboy
-dontwarn com.amazon.device.messaging.**
-dontwarn bo.app.**
-dontwarn com.appboy.ui.**
-dontwarn com.google.android.gms.**
-keep class bo.app.** { *; }
-keep class com.appboy.** { *; }
-keep class com.adjust.sdk.plugin.AndroidIdUtil { <methods>; }

-keep class com.google.android.gms.common.GooglePlayServicesUtil {
    int isGooglePlayServicesAvailable (android.content.Context);
}
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.** { *; }

#AirBnB DeepLinkDispatch
-keep class com.airbnb.deeplinkdispatch.** { *; }
-keepclasseswithmembers class * {
     @com.airbnb.deeplinkdispatch.DeepLink <methods>;
}

# Parcel library
-keep class **$$Parcelable { *; }

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter

# Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}