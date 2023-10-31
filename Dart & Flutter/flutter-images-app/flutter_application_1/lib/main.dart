import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_application_1/views/gallery_screen.dart'; // Importando a tela principal.
import 'package:flutter_application_1/providers/theme_notifier.dart'; // Importando o ThemeNotifier.

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => ThemeNotifier(),
      child: Builder(
        builder: (context) => MaterialApp(
          title: 'Galeria de Imagens',
          theme: Provider.of<ThemeNotifier>(context).getTheme(),
          home: const GalleryScreen(),
        ),
      ),
    );
  }
}
