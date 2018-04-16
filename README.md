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
