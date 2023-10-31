import 'package:flutter/material.dart';
import 'package:flutter_application_1/models/album.dart';
import 'package:flutter_application_1/views/album_detail_screen.dart';

class AlbumGridView extends StatefulWidget {
  final List<Album> albums;

  AlbumGridView({required this.albums});

  @override
  _AlbumGridViewState createState() => _AlbumGridViewState();
}

class _AlbumGridViewState extends State<AlbumGridView> {
  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        crossAxisSpacing: 16.0,
        mainAxisSpacing: 16.0,
      ),
      itemCount: widget.albums.length,
      itemBuilder: (context, index) {
        return GestureDetector(
          onTap: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) =>
                    AlbumDetailScreen(album: widget.albums[index]),
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
                Text(widget.albums[index].name),
              ],
            ),
          ),
        );
      },
    );
  }
}
