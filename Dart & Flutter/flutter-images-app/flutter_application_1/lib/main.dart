import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:photo_view/photo_view.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:shared_preferences/shared_preferences.dart';

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

class Album {
  final String name;
  final List<ImageAsset> images;

  Album({required this.name, required this.images});
}

class ImageAsset {
  final String url;
  final DateTime date;

  ImageAsset({required this.url, required this.date});
}

class AnimatedImageGrid extends StatefulWidget {
  final List<ImageAsset> imageAssets;
  final Function onDelete;
  final Function(int) onImageSelected;

  AnimatedImageGrid({
    required this.imageAssets,
    required this.onDelete,
    required this.onImageSelected,
  });

  @override
  _AnimatedImageGridState createState() => _AnimatedImageGridState();
}

class AlbumGridView extends StatefulWidget {
  final List<Album> albums;

  AlbumGridView({required this.albums});

  @override
  _AlbumGridViewState createState() => _AlbumGridViewState(albums: []);
}

class _AlbumGridViewState extends State<AlbumGridView> {
  final List<Album> albums;

  _AlbumGridViewState({required this.albums});

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        crossAxisSpacing: 16.0,
        mainAxisSpacing: 16.0,
      ),
      itemCount: albums.length,
      itemBuilder: (context, index) {
        return GestureDetector(
          onTap: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => AlbumDetailScreen(album: albums[index]),
              ),
            );
          },
          child: Card(
            elevation: 4.0,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(16.0),
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(albums[index].name),
              ],
            ),
          ),
        );
      },
    );
  }

  void _deleteAlbumConfirmation(BuildContext context, int index) {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Deletar Álbum'),
          content: Text(
              'Você tem certeza que deseja deletar este álbum e todas as suas imagens?'),
          actions: [
            TextButton(
              child: Text('Cancelar'),
              onPressed: () => Navigator.of(context).pop(),
            ),
            TextButton(
              child: Text('Deletar'),
              onPressed: () {
                setState(() {
                  widget.albums.removeAt(index);
                });
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}

class AlbumDetailScreen extends StatefulWidget {
  // Altere para StatefulWidget
  final Album album;

  AlbumDetailScreen({required this.album});

  @override
  _AlbumDetailScreenState createState() => _AlbumDetailScreenState();
}

class _AlbumDetailScreenState extends State<AlbumDetailScreen> {
  int get index => 0;

  // Classe State
  void _deleteImageFromAlbum(BuildContext context, int index) {
    setState(() {
      widget.album.images.removeAt(index);
    });
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.album.name),
        actions: [
          IconButton(
            padding: const EdgeInsets.only(right: 35.0),
            icon: Icon(Icons.delete),
            onPressed: () {
              _deleteImageFromAlbum(context, index);
            },
          ),
        ],
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          crossAxisSpacing: 16.0,
          mainAxisSpacing: 16.0,
        ),
        itemCount: widget.album.images.length,
        itemBuilder: (context, index) {
          return GestureDetector(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => ImageFullScreen(
                      imageUrl: widget.album.images[index].url,
                      onDelete: () {},
                    ),
                  ),
                );
              },
              child: Card(
                elevation: 4.0,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(16.0),
                ),
                child: Hero(
                  tag: widget.album.images[index].url,
                  child: CachedNetworkImage(
                    imageUrl: widget.album.images[index].url,
                    fit: BoxFit.cover,
                    placeholder: (context, url) =>
                        Center(child: CircularProgressIndicator()),
                    errorWidget: (context, url, error) =>
                        Center(child: Icon(Icons.error)),
                  ),
                ),
              ));
        },
      ),
    );
  }
}

class _AnimatedImageGridState extends State<AnimatedImageGrid> {
  List<int> _selectedIndices = [];

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        int columns = (constraints.maxWidth / 244).floor();
        return GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: columns,
            crossAxisSpacing: 16.0,
            mainAxisSpacing: 16.0,
          ),
          itemCount: widget.imageAssets.length,
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => ImageFullScreen(
                      imageUrl: widget.imageAssets[index].url,
                      onDelete: () => widget.onDelete(context, index),
                    ),
                  ),
                );
              },
              onLongPress: () {
                setState(() {
                  if (_selectedIndices.contains(index)) {
                    _selectedIndices.remove(index);
                  } else {
                    _selectedIndices.add(index);
                  }
                });
                widget.onImageSelected(index);
              },
              child: Card(
                elevation: 4.0,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(16.0),
                ),
                child: Stack(
                  fit: StackFit.expand,
                  children: [
                    Hero(
                      tag: widget.imageAssets[index].url,
                      child: CachedNetworkImage(
                        imageUrl: widget.imageAssets[index].url,
                        fit: BoxFit.cover,
                        placeholder: (context, url) =>
                            Center(child: CircularProgressIndicator()),
                        errorWidget: (context, url, error) =>
                            Center(child: Icon(Icons.error)),
                      ),
                    ),
                    if (_selectedIndices.contains(index))
                      Align(
                        alignment: Alignment.topRight,
                        child: Icon(Icons.check_circle, color: Colors.green),
                      ),
                  ],
                ),
              ),
            );
          },
        );
      },
    );
  }
}

class ImageFullScreen extends StatelessWidget {
  final String imageUrl;
  final Function onDelete;

  const ImageFullScreen({
    required this.imageUrl,
    required this.onDelete,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Imagem em Tela Cheia'),
        actions: [
          IconButton(
            padding: const EdgeInsets.only(right: 35.0),
            icon: Icon(Icons.delete),
            onPressed: () {
              onDelete();
              Navigator.of(context).pop();
            },
          ),
        ],
      ),
      body: PhotoView(
        imageProvider: NetworkImage(imageUrl),
        minScale: PhotoViewComputedScale.contained,
        maxScale: PhotoViewComputedScale.covered * 2,
        backgroundDecoration: BoxDecoration(
          color: Colors.black,
        ),
      ),
    );
  }
}

class ThemeNotifier extends ChangeNotifier {
  static final ThemeNotifier _instance = ThemeNotifier._internal();

  factory ThemeNotifier() {
    return _instance;
  }

  ThemeNotifier._internal();

  bool _isDarkTheme = false;

  ThemeData getTheme() {
    return _isDarkTheme ? _darkTheme : _lightTheme;
  }

  bool isDark() {
    return _isDarkTheme;
  }

  void toggleTheme() {
    _isDarkTheme = !_isDarkTheme;
    notifyListeners();
  }
}

final ThemeData _lightTheme = ThemeData(
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

final ThemeData _darkTheme = ThemeData(
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
