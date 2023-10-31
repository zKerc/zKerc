import 'package:flutter/material.dart';
import 'package:flutter_application_1/utils/themes.dart';

class ThemeNotifier extends ChangeNotifier {
  bool _isDarkTheme = false;

  ThemeData getTheme() {
    return _isDarkTheme ? darkTheme : lightTheme;
  }

  bool isDark() {
    return _isDarkTheme;
  }

  void toggleTheme() {
    _isDarkTheme = !_isDarkTheme;
    notifyListeners();
  }
}
