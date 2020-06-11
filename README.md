# Deprecated
This package was created to extend `WebView` from `react-native`. However, `react-native-webview` has [now added](https://github.com/react-native-community/react-native-webview/pull/325) the specific functionality this package was created for, so this package isn't needed anymore. 

Please use [`react-native-webview`](https://github.com/react-native-community/react-native-webview) going forward.

---

## react-native-android-fullscreen-webview-video

## Overview

The WebView component that comes with React Native doesn't have the ability to show videos
fullscreen by default. This package essentially copies the existing WebView for Android and adds some extra functionality to enable fullscreen videos. This behavior is supported by default for iOS, so this will simply use the native iOS WebView.

## Getting started

`yarn add react-native-android-fullscreen-webview-video`

or

`npm install react-native-android-fullscreen-webview-video --save`

##### Automatically link the library

`react-native link`

## Usage

This works exactly like the existing `WebView` component, so there is nothing extra that needs to be added.

```
import WebView from 'react-native-android-fullscreen-webview-video';

...

<WebView
  source={{ uri: 'https://www.youtube.com/embed/dQw4w9WgXcQ' }}
/>
```

---

MIT License.

---
