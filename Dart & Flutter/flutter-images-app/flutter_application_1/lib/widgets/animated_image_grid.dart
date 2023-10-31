import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_application_1/models/image_asset.dart';
import 'package:flutter_application_1/views/image_full_screen.dart';

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
