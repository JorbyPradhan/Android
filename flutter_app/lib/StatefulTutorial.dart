import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main(){
  runApp(myNewApp());
}

class myNewApp extends StatefulWidget {
  @override
  _myNewAppState createState() => _myNewAppState();
}

class _myNewAppState extends State<myNewApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "StateFul",
      theme: ThemeData(
        primarySwatch: Colors.cyan,
      ),
      home: homePage(),
    );
  }
}
class homePage extends StatefulWidget {
  @override
  _homePageState createState() => _homePageState();
}

class _homePageState extends State<homePage> {

  int dataToChange = 0;
  void datachange(){
    setState(() {
      dataToChange += 1;
    });
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "AppBar"
        ),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "$dataToChange",
              style: TextStyle(
                fontSize: 24.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            RaisedButton(
              onPressed: datachange,
              padding: EdgeInsets.symmetric(vertical: 15.0, horizontal: 20.0),
              child: Text(
                "Click Me",
                style: TextStyle(
                  fontSize: 24.0,
                  color: Colors.white,
                ),
              ),
              color: Colors.cyan,
            )
          ],
        ),
      ),
    );
  }
}

