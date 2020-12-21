import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

void main() {
  runApp(myapp());
}

class myapp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Simple App",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text(
            "My App",
            style: TextStyle(
              fontSize: 20.0,
              color: Colors.amberAccent,
            ),
          ),
        ),
        body: Center(
          child: Padding(
            padding: EdgeInsets.all(100.0),
            child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              Text(
                "This is a text widget",
                style: TextStyle(
                  fontSize: 20.0,
                ),
              ),
              RaisedButton(
                onPressed: () {},
                child: Text(
                  "Raised Button",
                  style: TextStyle(
                    fontSize: 20.0,
                  ),
                ),
              ),
              RaisedButton(
                onPressed: () {},
                child: Text(
                  "Raised Button 2",
                  style: TextStyle(
                    fontSize: 20.0,
                  ),
                ),
              )
            ],
          ),
        ),
        ),
        /* body: Center(
          child: IconButton(
            onPressed: (){},
            icon: Icon(
              Icons.add_a_photo,
            ),
            iconSize: 50.0,
            splashColor: Colors.green,
            tooltip: "Add A Photo",
          ),
          */ /*child: MaterialButton(
            color: Colors.red[400],
            height: 60.0,
            minWidth: 200.0,
            splashColor: Colors.cyan,
            onPressed: () {},
            child: Text(
              "Material Button",
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.white,
              ),
            ),
            shape: Border.all(color: Colors.red[800], width: 5.0),
           */ /**/ /* shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15.0)
            ),*/ /**/ /*
          ),*/ /*
          */ /*child: RaisedButton(
            splashColor: Colors.cyan,
            padding: EdgeInsets.all(10.0),
            highlightColor: Colors.indigo,
            onPressed: () {},
            child: Text(
              "Raised Button",
              style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
            ),
          ),*/ /*
        ),*/
        /*child: Text(
            "Welcome From Flutter, Your program is easy to learn and Basic Course",
            textAlign: TextAlign.start,
            softWrap: false,
            overflow: TextOverflow.ellipsis,
            maxLines: 2,
            style: new TextStyle(
              color: Colors.red,
              fontStyle: FontStyle.italic,
              fontSize: 40.0,
              fontWeight: FontWeight.bold,
              //fontFamily: "Times New Roman",
           */ /*   decoration: TextDecoration.overline,
              decorationColor: Colors.black,
              decorationStyle: TextDecorationStyle.dashed,*/ /*
              //background: Paint()..color = Colors.black..style=PaintingStyle.stroke,
            ),
          ),
        ),*/
      ),
    );
  }
}
