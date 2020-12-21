
import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:connectivity/connectivity.dart';

void main(){
  runApp(checkNetwork());
}

class checkNetwork extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Internet",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      debugShowCheckedModeBanner: false,
      home: MyNetworkPage(),
    );
  }
}
class MyNetworkPage extends StatefulWidget {
  @override
  _MyNetworkPageState createState() => _MyNetworkPageState();
}

class _MyNetworkPageState extends State<MyNetworkPage> {
 StreamSubscription connectivitySubscription;
 ConnectivityResult oldResult;

 @override
  void initState() {
    super.initState();

    connectivitySubscription = Connectivity().onConnectivityChanged.listen((ConnectivityResult resnow) {
      if(resnow == ConnectivityResult.none){
        print("Not Connected");
      }else if (oldResult == ConnectivityResult.none){
        if(resnow == ConnectivityResult.wifi){
          print("Wifi Connected");
        }else if(resnow == ConnectivityResult.mobile){
          print("mobile Connected");
        }
        print("Connected");
      }
      oldResult = resnow;
    });
  }
  @override
  void dispose() {
    super.dispose();
    connectivitySubscription.cancel();
  }
  /*void checkConnection() async{
    try{
      final result = await InternetAddress.lookup('google.com');
      if(result.isNotEmpty && result[0].rawAddress.isNotEmpty){
        print("Connected");
      }
    } on SocketException catch(_){
      print("No Connection");
    }
  }*/
 /* void checkConnection() async{
    var connectivityResult = await (Connectivity().checkConnectivity());
    if (connectivityResult == ConnectivityResult.mobile) {
      // I am connected to a mobile network.
      print("Internet");
    } else if (connectivityResult == ConnectivityResult.wifi) {
      // I am connected to a wifi network.
      print("wifi");
    }else if (connectivityResult == ConnectivityResult.none){
      print("no Connection");
    }
  }*/
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text("Status",
            style: TextStyle(
              fontSize: 30.0,
              fontWeight: FontWeight.w700,
              fontFamily: "Times New Roman",
            ),
            ),
            SizedBox(
              height: 10.0,
            ),
            FlatButton(onPressed: (){
             // checkConnection();
            },
                child: Text(
                  "Check!",
                  style: TextStyle(
                    fontSize: 30.0,
                    fontWeight: FontWeight.w700,
                    fontFamily: "Times New Roman",
                  ),
                ),
            ),
          ],
        ),
      )
    );
  }
}

