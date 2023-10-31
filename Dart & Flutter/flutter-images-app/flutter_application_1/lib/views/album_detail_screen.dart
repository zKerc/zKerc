import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_application_1/models/album.dart';
import 'package:flutter_application_1/views/image_full_screen.dart';

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
