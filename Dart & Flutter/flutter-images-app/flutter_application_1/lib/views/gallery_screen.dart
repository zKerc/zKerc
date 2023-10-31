import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_application_1/models/image_asset.dart';
import 'package:flutter_application_1/models/album.dart';
import 'package:flutter_application_1/widgets/animated_image_grid.dart';
import 'package:flutter_application_1/widgets/album_grid_view.dart';
import 'package:flutter_application_1/providers/theme_notifier.dart';
import 'package:shared_preferences/shared_preferences.dart';

class GalleryScreen extends StatefulWidget {
  const GalleryScreen({Key? key}) : super(key: key);

  @override
  _GalleryScreenState createState() => _GalleryScreenState();
}

class _GalleryScreenState extends State<GalleryScreen> {
  List<ImageAsset> imageAssets = [];
  List<Album> albums = [];
  List<int> _selectedIndices = [];
  int _currentIndex = 0;

  @override
  void initState() {
    super.initState();
    _loadImages();
    _loadAlbums().then((loadedAlbums) {
      setState(() {
        albums = loadedAlbums;
      });
    });
  }

  _loadImages() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> savedUrls = prefs.getStringList('imageUrls') ?? [];
    print('Dados carregados: $savedUrls');
    setState(() {
      imageAssets = savedUrls
          .map((url) => ImageAsset(url: url, date: DateTime.now()))
          .toList();
    });
  }

  Future<void> _addImage() async {
    TextEditingController urlController = TextEditingController();
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Adicionar imagem da URL'),
          content: TextField(
            controller: urlController,
            decoration: InputDecoration(hintText: "Insira a URL da imagem"),
          ),
          actions: [
            TextButton(
              child: Text('Cancelar'),
              onPressed: () => Navigator.of(context).pop(),
            ),
            TextButton(
              child: Text('Adicionar'),
              onPressed: () async {
                if (urlController.text.isNotEmpty) {
                  setState(() {
                    imageAssets.add(ImageAsset(
                        url: urlController.text, date: DateTime.now()));
                  });
                  await _saveImages();
                  Navigator.of(context).pop();
                }
              },
            ),
          ],
        );
      },
    );
  }

  _saveImages() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> urls = imageAssets.map((image) => image.url).toList();
    await prefs.setStringList('imageUrls', urls);
    print('Dados salvos: $urls');
  }

  void _navigateToHome(BuildContext context) {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (context) => GalleryScreen()),
    );
  }

  void _deleteImage(BuildContext context, int index) {
    setState(() {
      imageAssets.removeAt(index);
    });
    _saveImages();
    _navigateToHome(context);
  }

  _saveAlbums(List<Album> albums) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> albumData = albums.map((album) {
      return album.name +
          "|||" +
          album.images.map((image) => image.url).join("||");
    }).toList();
    await prefs.setStringList('albums', albumData);
    print('Álbuns salvos');
  }

  Future<List<Album>> _loadAlbums() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> albumData = prefs.getStringList('albums') ?? [];
    return albumData.map((data) {
      List<String> parts = data.split("|||");
      String name = parts[0];
      List<ImageAsset> images = parts[1]
          .split("||")
          .map((url) => ImageAsset(url: url, date: DateTime.now()))
          .toList();
      return Album(name: name, images: images);
    }).toList();
  }

  void _createAlbum(List<int> selectedIndices) async {
    TextEditingController albumNameController = TextEditingController();
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Nome do Álbum'),
          content: TextField(
            controller: albumNameController,
            decoration: InputDecoration(hintText: "Insira o nome do álbum"),
          ),
          actions: [
            TextButton(
              child: Text('Cancelar'),
              onPressed: () => Navigator.of(context).pop(),
            ),
            TextButton(
              child: Text('Criar'),
              onPressed: () async {
                if (albumNameController.text.isNotEmpty) {
                  List<ImageAsset> selectedImages = _selectedIndices
                      .map((index) => imageAssets[index])
                      .toList();
                  Album newAlbum = Album(
                      name: albumNameController.text, images: selectedImages);
                  albums.add(newAlbum);
                  _saveAlbums(albums);
                  setState(() {
                    _selectedIndices.clear();
                  });
                  Navigator.of(context).pop();
                }
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Minha Galeria de Imagens'),
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 35.0),
            child: IconButton(
              icon: Icon(Icons.add),
              onPressed: _addImage,
            ),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.photo_library),
            label: 'Fotos',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.album),
            label: 'Álbuns',
          ),
        ],
        onTap: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
      ),
      floatingActionButton: _selectedIndices.isNotEmpty
          ? FloatingActionButton(
              child: Icon(Icons.create_new_folder),
              onPressed: () => _createAlbum(_selectedIndices),
            )
          : FloatingActionButton(
              onPressed: () {
                final themeNotifier =
                    Provider.of<ThemeNotifier>(context, listen: false);
                themeNotifier.toggleTheme();
              },
              child: Icon(
                Provider.of<ThemeNotifier>(context).isDark()
                    ? Icons.brightness_7
                    : Icons.brightness_3,
              ),
            ),
      body: _currentIndex == 0
          ? imageAssets.isEmpty
              ? Center(
                  child: Text(
                    'Nenhuma imagem disponível. Toque no botão + para adicionar imagens.',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 16,
                      color: Theme.of(context).textTheme.headline6!.color,
                    ),
                  ),
                )
              : AnimatedImageGrid(
                  imageAssets: imageAssets,
                  onDelete: _deleteImage,
                  onImageSelected: (index) {
                    setState(() {
                      if (_selectedIndices.contains(index)) {
                        _selectedIndices.remove(index);
                      } else {
                        _selectedIndices.add(index);
                      }
                    });
                  },
                )
          : albums.isEmpty
              ? Center(
                  child: Text(
                    'Nenhum álbum disponível. Selecione algumas imagens e crie um álbum.',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 16,
                      color: Theme.of(context).textTheme.headline6!.color,
                    ),
                  ),
                )
              : AlbumGridView(albums: albums),
    );
  }

  void _sortImagesByDate() {
    setState(() {
      imageAssets.sort((a, b) => b.date.compareTo(a.date));
    });
  }

  void _sortImagesByName() {
    setState(() {
      imageAssets.sort((a, b) => a.url.compareTo(b.url));
    });
  }
}
