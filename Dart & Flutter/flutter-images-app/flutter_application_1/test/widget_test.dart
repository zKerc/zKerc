import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_application_1/main.dart';
import 'package:photo_view/photo_view.dart';
import 'package:http/http.dart' as http;
import 'package:mockito/mockito.dart';

class MockClient extends Mock implements http.Client {
  @override
  Future<http.Response> get(Uri url, {Map<String, String>? headers}) {
    return super.noSuchMethod(
      Invocation.method(#get, [url], {#headers: headers}),
      returnValue: Future.value(http.Response('OK', 200)),
      returnValueForMissingStub: Future.value(http.Response('OK', 200)),
    );
  }
}

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

  testWidgets('Adiciona uma imagem à galeria', (WidgetTester tester) async {
    await tester.pumpWidget(MyApp());

    // Clique no botão para adicionar uma imagem
    await tester.tap(find.byIcon(Icons.add));
    await tester.pumpAndSettle();

    // Insira uma URL válida no campo de texto do AlertDialog
    await tester.enterText(
        find.byType(TextField), 'https://example.com/image.jpg');

    // Clique no botão para confirmar a adição
    await tester.tap(find.text('Adicionar'));
    await tester.pumpAndSettle();

    // Verifique se a imagem foi adicionada à galeria
    // (Você pode verificar se o número de CachedNetworkImage aumentou, por exemplo)
  });

  testWidgets('Adiciona uma imagem à galeria', (WidgetTester tester) async {
    await tester.pumpWidget(MyApp());

    // Clique no botão para adicionar uma imagem
    await tester.tap(find.byIcon(Icons.add));
    await tester.pumpAndSettle();

    // Insira uma URL válida no campo de texto do AlertDialog
    await tester.enterText(
        find.byType(TextField), 'https://example.com/image.jpg');

    // Clique no botão para confirmar a adição
    await tester.tap(find.text('Adicionar'));
    await tester.pumpAndSettle();

    // Verifique se a imagem foi adicionada à galeria
    // (Você pode verificar se o número de CachedNetworkImage aumentou, por exemplo)
  });

  testWidgets('Teste de criação de álbum', (WidgetTester tester) async {
    await tester.pumpWidget(const MyApp());

    // Aguarda a construção do widget GalleryScreen.
    await tester.pumpAndSettle();

    // Supondo que você selecione imagens pressionando-as por um longo tempo:
    await tester.longPress(find.byType(Image).first);
    await tester.pumpAndSettle();

    // Toque no botão de criar álbum.
    await tester.tap(find.byIcon(Icons.create_new_folder));
    await tester.pumpAndSettle();

    // Encontra o TextField e insere um nome para o álbum.
    await tester.enterText(find.byType(TextField), 'Novo Álbum');

    // Toque no botão "Criar" no diálogo.
    await tester.tap(find.widgetWithText(TextButton, 'Criar'));
    await tester.pumpAndSettle();

    // Verifica se o álbum foi criado.
    expect(find.text('Novo Álbum'), findsOneWidget);
  });

  testWidgets('Teste de exclusão de álbum', (WidgetTester tester) async {
    await tester.pumpWidget(const MyApp());

    // Aguarda a construção do widget GalleryScreen.
    await tester.pumpAndSettle();

    // Toque no álbum.
    await tester.tap(find.text('Novo Álbum'));
    await tester.pumpAndSettle();

    // Toque no botão de exclusão.
    await tester.tap(find.byIcon(Icons.delete));
    await tester.pumpAndSettle();

    // Verifica se o álbum foi removido.
    expect(find.text('Novo Álbum'), findsNothing);
  });

  testWidgets('Altera o tema do aplicativo', (WidgetTester tester) async {
    await tester.pumpWidget(MyApp());

    // Verifique o tema inicial (por exemplo, verifique a cor de fundo)
    var bgColor =
        tester.widget<Scaffold>(find.byType(Scaffold)).backgroundColor;
    expect(bgColor, Colors.white);

    // Clique no botão para alterar o tema
    await tester.tap(find.byType(FloatingActionButton));
    await tester.pumpAndSettle();

    // Verifique se o tema foi alterado (por exemplo, verifique a nova cor de fundo)
    bgColor = tester.widget<Scaffold>(find.byType(Scaffold)).backgroundColor;
    expect(bgColor, Colors.black);
  });
}
