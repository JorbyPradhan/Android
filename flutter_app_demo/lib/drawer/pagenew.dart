import 'package:flutter/material.dart';
import 'package:flutter_app_demo/drawer/drawer.dart';

class PageNew extends StatefulWidget {
  @override
  _PageNewState createState() => _PageNewState();
}

class _PageNewState extends State<PageNew> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "New Page",
        ),
      ),
      drawer: CutomDrawer(),
      body: Center(
        child: FlutterLogo(
          size: 100.0,
          textColor: Colors.red,
        ),
      ),
    );
  }
}
