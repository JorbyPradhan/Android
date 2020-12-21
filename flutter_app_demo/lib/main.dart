import 'dart:async';

import 'package:flutter/material.dart';
import 'home.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "EditText",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyHomePage(),
    );
    /* return Container(
      decoration: BoxDecoration(
        color: Colors.white,
      ),
      child: Center(
        child: Image.asset('assets/images/download.png',
        fit: BoxFit.contain,
        ),
      ),
    );*/
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String myvar;
  String texttodisplay = "Default";

  void showText() {
    setState(() {
      texttodisplay = myvar;
    });
  }

  @override
  void initState() {
    super.initState();
    debugPrint("Stared The Splash Screen");
    Timer(Duration(seconds: 4), finished);
  }

  void finished() {
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(builder: (context) => home()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.red,
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                /*CircularProgressIndicator(
                backgroundColor: Colors.white,
              ),*/
                Text(
                  "Splash\nScreen",
                  style: TextStyle(
                    fontSize: 40.0,
                    color: Colors.white,
                    fontWeight: FontWeight.w800,
                    fontFamily: "Cursive",
                  ),
                ),
              ],
            )
          ],
        )
        /*appBar: AppBar(
        title: Text(
          "EditText"
        ),
      ),
      body: Column(
        children: <Widget>[
          Text(
            "$myvar"
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              maxLength: 20,
              maxLines: 2,
              onChanged: (text){
                myvar = text;
              },
              decoration: InputDecoration(
                prefixIcon: Icon(Icons.account_box),
                border: OutlineInputBorder(),
                hintText: "Enter Username",
              ),
              style: TextStyle(
                color: Colors.red,
                fontSize: 20.0,
              ),
            ),
          ),
          RaisedButton(
              onPressed: showText,
            child: Text(
              "Press ",
            ),
          ),
        ],
      ),*/
        );
  }
}
