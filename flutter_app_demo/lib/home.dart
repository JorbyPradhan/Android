import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:fluttertoast/fluttertoast.dart';

class home extends StatefulWidget {
  @override
  _homeState createState() => _homeState();
}

class _homeState extends State<home> {
  Icon customIcon = Icon(Icons.search);
  Widget customSearchBar = Text("AppBar");
  DateTime current;

  Future<bool> popped() {
    DateTime now = DateTime.now();
    if (current == null || now.difference(current) > Duration(seconds: 2)) {
      current = now;
      //Show toast
      Fluttertoast.showToast(
        msg: "Press Back Again to exit !",
        toastLength: Toast.LENGTH_SHORT,
      );
      return Future.value(false);
    } else {
      Fluttertoast.cancel();
      return Future.value(true);
    }
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Scaffold(
        appBar: AppBar(
          leading: IconButton(
            onPressed: () {},
            icon: Icon(Icons.menu),
          ),
          titleSpacing: 20.0,
          actions: <Widget>[
            IconButton(
                icon: customIcon,
                onPressed: () {
                  setState(() {
                    if (this.customIcon.icon == Icons.search) {
                      this.customIcon = Icon(Icons.cancel);
                      this.customSearchBar = TextField(
                        textInputAction: TextInputAction.go,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: "Search here",
                        ),
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 20.0,
                          fontWeight: FontWeight.w100,
                        ),
                      );
                    } else {
                      this.customIcon = Icon(Icons.search);
                      this.customSearchBar = Text("AppBar");
                    }
                  });
                }),
            IconButton(icon: Icon(Icons.more_vert), onPressed: () {}),
          ],
          /*bottom: PreferredSize(
          preferredSize: Size(50,50),
          child: Container(
          ),
        ),
        elevation: 20.0,*/
          title: customSearchBar,
        ),
        body: Container(
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                "Home Page",
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 20.0,
                  fontStyle: FontStyle.italic,
                  fontFamily: "Times New Roman",
                  fontWeight: FontWeight.w600,
                ),
              ),
              Padding(
                padding: EdgeInsets.all(10.0),
                child: FlatButton(
                  onPressed: () {
                    Navigator.pop(
                      context,
                    );
                  },
                  padding: EdgeInsets.symmetric(
                    horizontal: 25.0,
                    vertical: 10.0,
                  ),
                  color: Colors.red.shade400,
                  textColor: Colors.white,
                  child: Text(
                    "Splash Screen",
                    style: TextStyle(
                      fontSize: 26.0,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
      onWillPop: () => popped(),
      /* return showDialog(
              context: context,
              builder: (context) =>
                  AlertDialog(
                    title: Text(
                      "Warning",
                    ),
                    content: Text(
                      "Are You Sure To Exit ?",
                    ),
                    actions: <Widget>[
                      FlatButton(
                        onPressed: () {
                          Navigator.of(context).pop(true);
                        },
                        child: Text(
                            "Yes"
                        ),
                      ),
                      FlatButton(
                        onPressed: () {
                          Navigator.of(context).pop(false);
                        },
                        child: Text(
                            "No"
                        ),
                      ),
                    ],
                  ),
          );*/
    );
  }
}
