import 'dart:async';
import 'dart:io';

import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_app_demo/internetUi/imageUi.dart';

void main(){
  runApp(myUi());
}

class myUi extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Internet",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      debugShowCheckedModeBanner: false,
      home: MyInternetUI(),
    );
  }
}
class MyInternetUI extends StatefulWidget {
  @override
  _MyInternetUIState createState() => _MyInternetUIState();
}

class _MyInternetUIState extends State<MyInternetUI> {
  StreamSubscription connectivitySubscription1;
  ConnectivityResult previous;
  @override
  void initState() {
    super.initState();
    try {
      InternetAddress.lookup('google.com').then((value) {
        if (value.isNotEmpty && value[0].rawAddress.isNotEmpty) {
          Navigator.of(context).pushReplacement(
              MaterialPageRoute(builder: (context) =>
                  imageui(),
              ));
        } else {
          _showdialog();
        }
      }).catchError((onError) {
        _showdialog();
      });
    } on SocketException catch (_) {
      _showdialog();
    }

    connectivitySubscription1 =Connectivity().onConnectivityChanged.listen((ConnectivityResult conn) {
          if (conn == ConnectivityResult.none) {
            _showdialog();
          } else if (previous == ConnectivityResult.none) {
            /*if (conn == ConnectivityResult.wifi) {
              print("Wifi Connected");
            } else if (conn == ConnectivityResult.mobile) {
              print("mobile Connected");
            }*/
            Navigator.of(context).pushReplacement(
                MaterialPageRoute(builder: (context) =>
                    imageui(),
                ));
          }
          previous = conn;
        });
  }

  @override
  void dispose() {
    super.dispose();
    connectivitySubscription1.cancel();
  }
  void _showdialog(){
    showDialog(context: context,
    builder: (context) => AlertDialog(
      title: Text('ERROR'),
      content: Text("No Internet Detected"),
      actions: <Widget>[
        FlatButton(onPressed: () => SystemChannels.platform.invokeMethod('Systemnavigator.pop'), child: Text("Exit"),)
      ],
    ));
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            CircularProgressIndicator(),
            Padding(padding: EdgeInsets.only(top: 20.0),
            child: Text(
              "Checking Your Internet Connection."
            ),),

          ],
        ),
      ),
    );
  }
}

