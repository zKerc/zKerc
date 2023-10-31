import 'package:flutter/material.dart';

final ThemeData lightTheme = ThemeData(
  primaryColor: Colors.blueGrey,
  scaffoldBackgroundColor: Colors.white,
  appBarTheme: AppBarTheme(
    backgroundColor: Colors.blueGrey,
  ),
  textTheme: TextTheme(
    headline6: TextStyle(
      color: Colors.black,
      fontSize: 20.0,
    ),
  ),
);

final ThemeData darkTheme = ThemeData(
  primaryColor: Colors.blueGrey,
  scaffoldBackgroundColor: Colors.black,
  appBarTheme: AppBarTheme(
    backgroundColor: Colors.blueGrey,
  ),
  bottomNavigationBarTheme: BottomNavigationBarThemeData(
    backgroundColor: Colors.black,
    selectedItemColor: Colors.blueGrey,
    unselectedItemColor: Colors.grey,
  ),
  textTheme: TextTheme(
    headline6: TextStyle(
      color: Colors.white,
      fontSize: 20.0,
    ),
  ),
);
