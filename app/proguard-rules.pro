# =====================================================================
# ZaabuTV Production ProGuard / R8 Rules
# Optimized for maximum code & resource shrinking without runtime breaks
# =====================================================================

# Retain stack traces & annotations for production crash debugging
-keepattributes SourceFile,LineNumberTable,Signature,InnerClasses,EnclosingMethod,Exceptions,*Annotation*
-renamesourcefileattribute SourceFile

# ─── 1. Domain Models & Data Structures ───────────────────────────────
-keep class com.engineerfred.zaabutv.domain.model.** { *; }

# ─── 2. Type-Safe Navigation & Kotlin Serialization ───────────────────
-keep class com.engineerfred.zaabutv.navigation.Screen** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}
-keepclassmembers class * implements kotlinx.serialization.KSerializer {
    public static final ** INSTANCE;
}
-keepclassmembers class * {
    *** Companion;
}
-keepclassmembers class * {
    public static *** serializer(...);
}

# ─── 3. Dagger Hilt Dependency Injection ──────────────────────────────
-keep class * extends android.app.Application
-keep class com.engineerfred.zaabutv.ZaabuTVApp** { *; }
-keep class com.engineerfred.zaabutv.di.** { *; }
-keep class * extends androidx.lifecycle.ViewModel
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
    @dagger.Provides <fields>;
    @dagger.Provides <methods>;
}
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.UnsafeCasts

# ─── 4. DataStore Preferences ─────────────────────────────────────────
-keep class androidx.datastore.** { *; }
-keep class com.engineerfred.zaabutv.data.datastore.** { *; }

# ─── 5. Coil 3 & OkHttp Image Loader ──────────────────────────────────
-keep class coil3.** { *; }
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn coil3.**

# ─── 6. Google Fonts & Compose Runtime ────────────────────────────────
-keep class androidx.compose.ui.text.font.** { *; }
-keep class androidx.compose.** { *; }

# ─── 7. Kotlin Coroutines & Flow ──────────────────────────────────────
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**
