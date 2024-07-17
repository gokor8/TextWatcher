# Compose Mask Library

[![JitPack](https://jitpack.io/v/gokor8/TextWatcher.svg)](https://jitpack.io/#gokor8/TextWatcher)
[![License: Apache 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

**TextWatcher it is compose Mask Library.**

It is easy-to-use library for adding input masks to your Jetpack Compose applications. It allows you to easily format input data, such as phone numbers, dates, currencies, and more, with minimal effort.

## Features

- **Easy Integration**: Easily add input masks to your Compose TextField components.
- **Flexibility**: Customize masks for any data format.

## Installation

Add the library to your project using JitPack. First, add the JitPack repository to your `build.gradle.kts` file:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

Then, add the dependency to your build.gradle.kts file:

```
dependencies {
    implementation("com.github.gokor8:TextWatcher:0.1")
}
```

## Usage

Here's an example of how to use the Compose Mask Library to create an input mask for a phone number:

```
var text by remember { mutableStateOf("") }
val transformation = remember {
  MaskVisualTransformation(
     MaskStore.Default(
       MaskUnit.Static('+'),
       MaskUnit.Replace('7'),
       MaskUnit.Empty()
     ),
     Limiter.Limited(1)
   )
}

MaskedTextField(text, transformation) { text = it }
```

**You can also use** ```TextField```

## License

Compose Mask Library is distributed under the Apache
