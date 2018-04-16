import { Platform } from 'react-native';
import androidWebView from './androidWebView';
import iosWebView from './iosWebView';

const CustomWebView = Platform.OS === 'ios' ? iosWebView : androidWebView;

module.exports = CustomWebView;
