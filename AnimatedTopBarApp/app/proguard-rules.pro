# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/your_user/Library/Android/sdk/tools/proguard/proguard-android-optimize.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If you use reflection, typically to load classes dynamically, you will need
# to include additional keep options specifying the classes that you wish to
# keep. This may be required for libraries that use reflection.
#-keep public class com.example.MyClass
#-keepclassmembers class com.example.MyClass {
#   public <fields>;
#   public <methods>;
#}

# If your project uses WebView with HTML that calls back to Java using JavaScript
# interfaces, then you will need to add the following keep option:
#-keepclassmembers class * {
#    @android.webkit.JavascriptInterface <methods>;
#}

# If your project uses a library that itself uses reflection, you may need
# to include a keep option for that library.
#-keep class com.google.gson.Gson

# If your project uses an enum that is serialized or deserialized using Gson,
# you may need to include a keep option for the enum.
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

# If you are using Kotlin Coroutines, add the following rules to prevent issues with obfuscation:
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory { *; }
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory { *; }
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler { *; }
-keepnames class kotlinx.coroutines.CoroutineName { *; }
-keepnames class kotlinx.coroutines.Job { *; }
-keepnames class kotlinx.coroutines.SupervisorJob { *; }
-keepnames class kotlinx.coroutines.CompletableJob { *; }
-keepnames class kotlinx.coroutines.flow.** { *; }
-keepclassmembernames class kotlinx.coroutines.flow.internal.AbstractSharedFlowKt {
    java.lang.Object[] getBuffer();
}
-keepclassmembernames class kotlinx.coroutines.flow.internal.ChannelFlow {
    kotlinx.coroutines.channels.ReceiveChannel produceImpl(kotlinx.coroutines.CoroutineScope);
}
-keepclassmembernames class kotlinx.coroutines.flow.internal.FusibleFlow {
    kotlinx.coroutines.flow.Flow fuse(kotlinx.coroutines.CoroutineContext,int,kotlinx.coroutines.channels.BufferOverflow);
}

# Keep Jetpack Compose specific rules
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}
-keepclassmembers class * {
    @androidx.compose.runtime.InternalComposeApi <methods>;
}
-keepclassmembers class * {
    @androidx.compose.ui.tooling.preview.Preview <methods>;
}
-keepclassmembers class * {
    @androidx.compose.ui.tooling.preview.PreviewParameterProvider <methods>;
}
-keepclassmembernames class * {
    @androidx.compose.runtime.Composable <methods>;
}
-keepclassmembernames class * {
    @androidx.compose.runtime.InternalComposeApi <methods>;
}
-keepclassmembernames class * {
    @androidx.compose.ui.tooling.preview.Preview <methods>;
}
-keepclassmembernames class * {
    @androidx.compose.ui.tooling.preview.PreviewParameterProvider <methods>;
}
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes Signature

# Keep default constructors for classes used with reflection, such as those used by Firebase.
-keepclassmembers,allowobfuscation class * {
  <init>();
}
