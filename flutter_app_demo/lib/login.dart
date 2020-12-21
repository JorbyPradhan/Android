import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'home.dart';
import 'package:fluttertoast/fluttertoast.dart';

void main() {
  runApp(MyLogin());
}

class MyLogin extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Login",
      theme: ThemeData(primarySwatch: Colors.red),
      home: MyLoginPage(),
    );
  }
}

class MyLoginPage extends StatefulWidget {
  @override
  _MyLoginPageState createState() => _MyLoginPageState();
}

class _MyLoginPageState extends State<MyLoginPage> {
  String userName = "";
  String pass = "";
  String errorMessage = "";

  void showToast(String message){
    Fluttertoast.showToast(
        msg: "$message",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
        timeInSecForIosWeb: 1,
        backgroundColor: Colors.red,
        textColor: Colors.white,
        fontSize: 16.0
    );
  }
  void checkValid(){
    if(userName == "" || pass == ""){
      errorMessage = "Plz Fill UserName and password";
      showToast(errorMessage);
      return;
    }else{
      if(userName == "James" && pass == "123"){
        errorMessage= "Login Success";
        showToast(errorMessage);
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => home(),
          ),
        );
      }else{
        errorMessage = "Login Fail";
        showToast(errorMessage);
        return;
      }
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              maxLines: 1,
              maxLength: 30,
              onChanged: (text){
                userName = text;
              },
              decoration: InputDecoration(
                hintText: "Enter UserName",
                prefixIcon: Icon(Icons.account_box),
                border: OutlineInputBorder(),
              ),
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.red,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              maxLength: 20,
              maxLines: 1,
              onChanged: (text){
                pass = text;
              },
              decoration: InputDecoration(
                hintText: "Enter Password",
                prefixIcon: Icon(Icons.vpn_key),
                border: OutlineInputBorder(),
              ),
              obscureText: true,
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.red,
              ),
            ),
          ),
          MaterialButton(
            height: 60.0,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15.0)
            ),
            padding: EdgeInsets.all(25.0),
            onPressed: () {

              checkValid();
            },
            elevation: 10.0,
            highlightElevation: 30.0,
            child: Text(
              "Login",
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 26.0,
                fontWeight: FontWeight.bold,
                color: Colors.red,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
