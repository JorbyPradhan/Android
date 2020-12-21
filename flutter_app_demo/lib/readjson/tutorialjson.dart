import 'dart:convert';

import 'package:flutter/material.dart';

void main(){
  runApp(myJson());
}

class myJson extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Read Json",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyJson(),
    );
  }
}

class MyJson extends StatefulWidget {
  @override
  _MyJsonState createState() => _MyJsonState();
}

class _MyJsonState extends State<MyJson> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey,
      body: FutureBuilder(
        future: DefaultAssetBundle.of(context).loadString("assets/data.json"),
        builder: (context, snapshot){
          var mydata = json.decode(snapshot.data.toString());
          if(mydata == null){
            return Center(
              child: Text(
                "Loading",
                style: TextStyle(
                  fontSize: 26.0,
                ),
              ),
            );
          }else {
            return Center(
              child: Text(
                "${mydata[2]["name"]} \n ${mydata[2]["age"]}",
                style: TextStyle(
                  fontSize: 26.0,
                  fontWeight: FontWeight.w700,
                ),
              ),
            );
          }
        },
      ),
    );
  }
}

