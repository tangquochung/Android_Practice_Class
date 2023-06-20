@echo off
"C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=24" ^
  "-DANDROID_PLATFORM=android-24" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\build\\intermediates\\cxx\\Debug\\6wq4k5b4\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\build\\intermediates\\cxx\\Debug\\6wq4k5b4\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\.cxx\\Debug\\6wq4k5b4\\arm64-v8a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
