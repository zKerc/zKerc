import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_application_1/main.dart';
import 'package:photo_view/photo_view.dart';

void main() {
  testWidgets('Teste de widget de galeria', (WidgetTester tester) async {
    // Constrói nosso widget de galeria e aciona uma renderização.
    await tester.pumpWidget(const MyApp());

    // Aguarda a construção do widget GalleryScreen.
    await tester.pumpAndSettle();

    // Encontra o primeiro widget de imagem na galeria.
    final imageFinder = find.byType(Image);

    // Verifica se a imagem está presente na tela.
    expect(imageFinder, findsWidgets);

    // Toque na primeira imagem.
    await tester.tap(imageFinder.first);

    // Aguarda a animação de transição para a tela cheia.
    await tester.pumpAndSettle();

    // Encontra o widget de imagem em tela cheia.
    final fullScreenImageFinder = find.byType(PhotoView);

    // Verifica se a imagem em tela cheia está presente na tela.
    expect(fullScreenImageFinder, findsOneWidget);

    // Verifica se a barra de aplicativo em tela cheia está presente.
    expect(find.byType(AppBar), findsOneWidget);

    // Toque na imagem em tela cheia para voltar à galeria.
    await tester.tap(fullScreenImageFinder);

    // Aguarda a animação de retorno à galeria.
    await tester.pumpAndSettle();

    // Verifica se a galeria está novamente visível.
    expect(imageFinder, findsWidgets);
  });
}
