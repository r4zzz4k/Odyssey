import org.gradle.internal.impldep.org.codehaus.plexus.util.Os

plugins {
    `kotlin-dsl`
}

val compileSdkVersion by extra(31)
val targetSdkVersion by extra(30)
val minSdkVersion by extra(21)

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
    }
}

buildscript {
    val kotlin_version by extra("1.5.21")
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}
