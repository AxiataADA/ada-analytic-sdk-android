# ADA Analytics SDK for Android

[![License](https://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/AxiataADA/ada-analytic-sdk-android)
[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)](https://github.com/AxiataADA/ada-analytic-sdk-android)

## Introduction 
The ADAAnalytics Android SDK using Maven and will be added as a project dependency on build.gradle file.  

Before proceeding further with the integration, you will need to obtain following items:  
 
- App name which is the unique name to identify your app (you can generate this yourself).  
- Base URL which is the URL of the SDK server API. (your server ip address)  
- App Key the key used to authenticate into SDK server (will be provided by us)  
- App Secret the secret key use to authenticate to the SDK server (will be provided by us) 

## Minimum Requirements 
- Android 4.0.3 and above 
- JDK 1.8 and above 
 
## Installation
#### Adding the Library to Your Android Studio Project  

- Add these code to your Project build.gradle file

```gradle
allprojects { 
   repositories { 
 
      ... 
 
      maven { 
         url 'http://ada-analytic-sdk-android.s3-website-us-east-1.amazonaws.com' 
      } 
   } 
} 
```

- Add the dependencies into the Module build.gradle 

```gradle
dependencies { 
   implementation 'com.ada.utility:analytics:1.0.2' 
} 
```

## Usage
#### SDK Initialization 
Initialize the SDK in the first activity with your `APP_NAME`, `URL`, `APP_SECRET` and `API_KEY` given from us. 

**Kotlin**
```kotlin
ADAAnalytic.sharedInstance.setup(APP_NAME , URL ,APP_KEY , APP_SECRET,this) 
```

**Java**
```java
ADAAnalytic.getSharedInstance().setup(APP_NAME , URL ,APP_KEY , APP_SECRET,this) 
```

#### Sending Data to SDK Server 
To log an event to server simply call this method and pass the `EVENT_NAME` along with any additional data if any (optional). 

**Kotlin**
```kotlin
ADAAnalytic.sharedInstance.logEvent(EVENT_NAME, mapOf(OPTIONAL_KEY_1 to OPTIONAL_VALUE_1)) 
```

**Java**
```java
Map<String, String> map = new HashMap<String, String>(){
            {
                put("OPTIONAL_KEY_1", "OPTIONAL_VALUE_1");
                put("OPTIONAL_KEY_2", "OPTIONAL_KEY_2");
            }
        };
ADAAnalytic.getSharedInstance().logEvent(EVENT_NAME, map) 
```

## Author

[ADA Asia](https://ada-asia.com)
