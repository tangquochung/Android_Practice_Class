@echo off
"C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Asus\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\build\\intermediates\\cxx\\Debug\\432g6r30\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\build\\intermediates\\cxx\\Debug\\432g6r30\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Education\\Practice\\Code\\Android Studio\\Android_Practice_Class\\Week6\\OpenCV\\.cxx\\Debug\\432g6r30\\x86" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
