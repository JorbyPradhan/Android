import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

void main() {
  runApp(myCalculatorApp());
}

class myCalculatorApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Calculator",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  int firstnum;
  int secondnum;
  String texttodisplay = "";
  String res;
  String operatortoperform;

  void btnClicked(String btnVal){
      if(btnVal == "C"){
        texttodisplay = "";
        firstnum = 0;
        secondnum = 0;
        res = "";
      }
      else if(btnVal == "+" || btnVal == "-" || btnVal == "x" || btnVal=="/"){
        firstnum = int.parse(texttodisplay);
        res = "";
        operatortoperform = btnVal;
      }
      else if(btnVal == "="){
        secondnum = int.parse(texttodisplay);
        if(operatortoperform == "+"){
          res = (firstnum + secondnum).toString();
        }
        if(operatortoperform == "-"){
          res = (firstnum - secondnum).toString();
        }
        if(operatortoperform == "x"){
          res = (firstnum * secondnum).toString();
        }
        if(operatortoperform == "/"){
          res = (firstnum / secondnum).toString();
        }
      }
      else{
        res = int.parse(texttodisplay + btnVal).toString();
      }
      setState(() {
        texttodisplay = res;
      });
  }
  Widget custombutton(String btnval){
    return Expanded(
        child: OutlineButton(
          padding: EdgeInsets.all(25.0),
          onPressed: () => btnClicked(btnval),
          child: Text(
            "$btnval",
            style: TextStyle(
              fontSize: 25.0
            ),
          ),
        ),
    );
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Calculator"),
      ),
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            Expanded(
              child: Container(
                padding: EdgeInsets.all(10.0),
                alignment: Alignment.bottomRight,
                child: Text(
                  "$texttodisplay",
                  style: TextStyle(
                    fontSize: 30.0,
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ),
            ),
            Row(
              children: <Widget>[
                custombutton("7"),
                custombutton("8"),
                custombutton("9"),
                custombutton("+"),
              ],
            ),
            Row(
              children: <Widget>[
                custombutton("4"),
                custombutton("5"),
                custombutton("6"),
                custombutton("-"),
              ],
            ),
            Row(
              children: <Widget>[
                custombutton("1"),
                custombutton("2"),
                custombutton("3"),
                custombutton("x"),
              ],
            ),
            Row(
              children: <Widget>[
                custombutton("C"),
                custombutton("0"),
                custombutton("="),
                custombutton("/"),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
