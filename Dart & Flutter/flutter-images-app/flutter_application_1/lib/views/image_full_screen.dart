import 'package:flutter/material.dart';
import 'package:photo_view/photo_view.dart';

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
