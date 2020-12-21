import 'package:flutter/material.dart';
import 'package:flutter_app_demo/drawer/drawer.dart';
import 'package:flutter_app_demo/drawer/pagenew.dart';


void main() {
  runApp(MyDrawer());
}

class MyDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Material Drawer",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyDrawerPage(),
    );
  }
}

class MyDrawerPage extends StatefulWidget {
  @override
  _MyDrawerPageState createState() => _MyDrawerPageState();
}

class _MyDrawerPageState extends State<MyDrawerPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "MyApp",
        ),
      ),
      drawer: CutomDrawer(),
      body: Center(
        child: FlutterLogo(
          size: 100.0,
        ),
      ),
    );
  }
}
